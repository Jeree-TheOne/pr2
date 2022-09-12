package ru.spring.pr2.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.spring.pr2.model.Role;
import ru.spring.pr2.model.User;
import ru.spring.pr2.repo.UserRepository;

import java.util.Collections;

@Controller
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/registration")
    public String reg_view(Model model)
    {
        return "registration";
    }

    @PostMapping("/registration")
    public String reg_action(User user, Model model)
    {
        User userFromDB = userRepository.findByUsername(user.getUsername());
        if (userFromDB != null)
        {
            model.addAttribute(
                    "error",
                    "Такой пользователь уже существует");
            return "registration";
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepository.save(user);
        return "redirect:/login";
    }
}