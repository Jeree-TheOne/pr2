package ru.spring.pr2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.spring.pr2.model.Chel;
import ru.spring.pr2.repo.ChelRepository;

import java.util.List;

@Controller
@RequestMapping("/chel")
public class ChelController {

    @Autowired
    ChelRepository chelRepository;

//    Get mappings
    @GetMapping("/")
    public String main(Model model) {
        return "redirect:/chel/registration";
    }

    @GetMapping("/registration")
    public String reg(Model model) {
        model.addAttribute("pageTitle", "Registration");
        return "chels/Registration";
    }

    @GetMapping("/all")
    public String all(Model model) {
        Iterable<Chel> chels = chelRepository.findAll();
        model.addAttribute("chels", chels);
        model.addAttribute("pageTitle", "All");
        return "chels/AllChels";
    }

//    Post mappings
    @PostMapping("/add")
    public String add(@RequestParam("name") String name,
                      @RequestParam("nickname") String nickname,
                      @RequestParam("gender") String gender,
                      @RequestParam("age") Integer age,
                      @RequestParam("isZoomer") Boolean isZoomer,
                      Model model) {

        Chel newUser = new Chel(name, nickname, gender, age, isZoomer);
        chelRepository.save(newUser);
        return "redirect:/chel/all";
    }

//    Search post mappings
    @GetMapping("/search")
    public String search(@RequestParam("name") String name,
                         Model model) {
        List<Chel> chelList = chelRepository.findByNicknameContains(name);
        model.addAttribute("chels", chelList);
        return "chels/AllChels";
    }
}
