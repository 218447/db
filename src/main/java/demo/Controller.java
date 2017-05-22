package demo;

import demo.account.AccountValidator;
import demo.account.User;
import demo.account.UserDAO;
import demo.account.UserDAOImpl;
import demo.model.AtmosphericData;
import demo.model.WeatherDAO;
import demo.model.WeatherDAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private WeatherDAO weatherDAO;

    @Autowired
    private AccountValidator accountValidator;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(accountValidator);
    }


    @RequestMapping("/*")
    public String index() {
        return "any";
    }

    @RequestMapping(value="/login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerPage () {
        return "register";
    }

    @RequestMapping(value = "/registering", method = RequestMethod.POST)
    public String registeringPage(@RequestParam("username") String username, @RequestParam ("password") String password,
                                  BindingResult result) {
        User newUser = new User(username, password);
        accountValidator.validate(newUser, result);

        if (result.hasErrors()) {
            return "redirect:error";
        }
        userDAO.registerUser(username, password);
        return "login";
    }

    @RequestMapping(value="/forecast", method = RequestMethod.GET)
    public String forecastPage () {
        return "forecast";
    }

    @RequestMapping(value="/administration", method = RequestMethod.GET)
    public String adminPage () {
        return "admin";
    }

    @RequestMapping(value = "/submit", method = RequestMethod.GET)
    public String submittingPage () {
        return "submit";
    }

    @RequestMapping(value = "/prognosis", method = RequestMethod.POST)
    public String prognosisPage (@RequestParam("past") int past, @RequestParam("future") int future, ModelMap model) {
        List<AtmosphericData> data = weatherDAO.getAtmosphericDataPast(past);

        //forecastAlgorithm.compute(data, future)
        //model.addAttribute(forecast)

        return "prognosis";
    }
}