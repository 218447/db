package demo.controller;

import demo.account.AccountValidator;
import demo.account.Account;
import demo.account.AccountDAO;
import demo.model.*;
import demo.storage.StorageFileNotFoundException;
import demo.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.stream.Collectors;

@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    private AccountDAO accountDAO;

    @Autowired
    private WeatherDAO weatherDAO;

    @Autowired
    private Algorithm algorithm;

    @Autowired
    private AccountValidator accountValidator;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(accountValidator);
    }

    private final StorageService storageService;

    @Autowired
    public Controller(StorageService storageService) {
        this.storageService = storageService;
    }

    @RequestMapping("/*")
    public String index() {
        return "forecast";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerPage() {
        return "register";
    }

    @RequestMapping(value = "/registering", method = RequestMethod.POST)
    public String registeringPage(@RequestParam("username") String username, @RequestParam("password") String password,
                                  BindingResult result) {
        Account newAccount = new Account(username, password, true);
        accountValidator.validate(newAccount, result);

        if (result.hasErrors()) {
            return "redirect:error";
        }
        accountDAO.registerUser(username, password);
        return "login";
    }

    @RequestMapping(value = "/forecast", method = RequestMethod.GET)
    public String forecastPage() {
        return "forecast";
    }

    @RequestMapping(value = "/prognosis", method = RequestMethod.POST)
    public String calculatingPage(@RequestParam("city") String city, ModelMap model) {

        double[] months = new double[12];
        Prognosis prognosis = makePrognosisIfNone(city, months);

        model.addAttribute("mean", prognosis.getTemperatureMean());
        model.addAttribute("city", city);
        model.addAttribute("months", months);

        return "prognosis";
    }

    private Prognosis makePrognosisIfNone(String city, double[] months) {
            algorithm.setCity(new City(city));
            algorithm.analysis();

            for (int i = 0; i < 12; i++) {
                months[i] = algorithm.countApproximation(i);
            }
            Prognosis prognosis = new Prognosis(new City(city), algorithm.yearMean(), algorithm.getCityFunction().toString());
            weatherDAO.insertPrognosis(prognosis);
            return prognosis;
    }

    @GetMapping("/admin")
    public String adminPage(Model model) throws IOException {

        return "admin";
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+file.getFilename()+"\"")
                .body(file);
    }

    @PostMapping("/admin")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {

        storageService.store(file);
        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");

        return "admin";
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }
}