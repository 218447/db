package demo.controller;

import demo.account.AccountValidator;
import demo.account.User;
import demo.account.UserDAO;
import demo.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        User newUser = new User(username, password);
        accountValidator.validate(newUser, result);

        if (result.hasErrors()) {
            return "redirect.js:error";
        }
        userDAO.registerUser(username, password);
        return "login";
    }

    @RequestMapping(value = "/forecast", method = RequestMethod.GET)
    public String forecastPage() {
        return "forecast";
    }

    @RequestMapping(value = "/administration", method = RequestMethod.GET)
    public String adminPage() {
        return "admin";
    }

    @RequestMapping(value = "/prognosis", method = RequestMethod.GET)
    public String calculatingPage(@RequestParam("city") String city, ModelMap model) {

        double[] months = new double[12];
        makePrognosisIfNone(city, months);

        List<Prognosis> prognosis = weatherDAO.getPrognosisByCity(city);
        model.addAttribute("mean", prognosis.get(0).getTemperatureMean());
        model.addAttribute("city", city);
        model.addAttribute(months);

        return "prognosis";
    }

    private void makePrognosisIfNone(String city, double[] months) {
        if (weatherDAO.getPrognosisByCity(city) == null) {
            Algorithm algorithm = new Algorithm(new City(city));
            algorithm.analysis();
            for (int i = 0; i < 12; i++) {
                months[i] = algorithm.countApproximation(i);
            }
            Prognosis prognosis = new Prognosis(new City(city), algorithm.yearMean(), algorithm.getCityFunction().toString());
            weatherDAO.insertPrognosis(prognosis);
        }
    }
}