package ru.spring.pr2.controllers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class JavaSpringController {

    @GetMapping("/")
    public String greeting(Model model) {
        model.addAttribute("title", "Home page");
        return "Home";
    }

    @GetMapping("/calculator")
    public String Calc(Model model) {
        model.addAttribute("title", "Calculator");
        return "Calculator";
    }

    @PostMapping("/result")
    public String Result(@RequestParam(value = "number1", required = false, defaultValue = "1") int a,
                         @RequestParam(value = "number2", required = false, defaultValue = "2") int b,
                         @RequestParam(value = "action", required = false) String action,
                         Model model) {

        int c = 0;

        switch (action) {
            case "+" -> {
                c = a + b;
                break;
            }
            case "-" -> {
                c = a - b;
                break;
            }
            case "*" -> {
                c = a * b;
                break;
            }
            case "/" -> {
                c = a / b;
                break;
            }
        }

        model.addAttribute("title", "Result page");
        model.addAttribute("answer", c);
        return "ResultPage";
    }
}
