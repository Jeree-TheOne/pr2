package ru.spring.pr2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.spring.pr2.model.Role;
import ru.spring.pr2.model.User;
import ru.spring.pr2.repo.UserRepository;

import javax.validation.Valid;
import java.util.Collections;

@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;


    @GetMapping("/registration")
    public String reg_view(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @GetMapping("/login")
    public String log_view(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/registration")
    public String reg_action(@Valid User user,
                             BindingResult bindingResult,
                             Model model) {

        if (bindingResult.hasErrors()) {
            return "/registration";
        }

        User userFromDB = userRepository.findByUsername(user.getUsername());
        if (userFromDB != null)
        {
            model.addAttribute(
                    "error",
                    "Такой пользователь уже существует");
            return "/registration";
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepository.save(user);
        return "redirect:/login";
    }
}