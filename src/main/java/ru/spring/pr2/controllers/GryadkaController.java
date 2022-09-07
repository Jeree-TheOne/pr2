package ru.spring.pr2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.spring.pr2.model.Ovoshi;
import ru.spring.pr2.repo.GryadkaRepository;

import java.util.List;

@Controller
@RequestMapping("/gryadka")
public class GryadkaController {
    @Autowired
    GryadkaRepository gryadkaRepository;

//    Get mappings
    @GetMapping("/")
    public String main(Model model) {
        return "redirect:/gryadka/all";
    }

    @GetMapping("/add")
    public String reg(Model model) {
        model.addAttribute("pageTitle", "Add ovosh in gryadka");
        return "gryadka/AddOvosh";
    }

    @GetMapping("/all")
    public String all(Model model) {
        Iterable<Ovoshi> ovoshi = gryadkaRepository.findAll();
        model.addAttribute("ovoshi", ovoshi);
        model.addAttribute("pageTitle", "All ovoshi in gryadk");
        return "gryadka/AllOvoshi";
    }

    //    Post mappings
    @PostMapping("/add")
    public String add(@RequestParam("name") String name,
                      @RequestParam("color") String color,
                      @RequestParam("IQ") Integer IQ,
                      Model model) {

        Ovoshi newOvosh = new Ovoshi(name, color, IQ);
        gryadkaRepository.save(newOvosh);
        return "redirect:/gryadka/all";
    }

    //    Search post mappings
    @GetMapping("/search")
    public String search(@RequestParam("name") String name,
                         Model model) {
        List<Ovoshi> ovoshiList = gryadkaRepository.findByNameContains(name);
        model.addAttribute("ovoshi", ovoshiList);
        return "gryadka/AllOvoshi";
    }

}
