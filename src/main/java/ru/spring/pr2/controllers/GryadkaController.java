package ru.spring.pr2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.spring.pr2.model.Ovoshi;
import ru.spring.pr2.repo.GryadkaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @GetMapping("/{id}")
    public String read (@PathVariable("id") Long id, Model model) {
        Optional<Ovoshi> Ovoshi = gryadkaRepository.findById(id);
        ArrayList<Ovoshi> arrayList = new ArrayList<>();
        Ovoshi.ifPresent(arrayList::add);
        model.addAttribute("Ovoshi", arrayList);
        return "gryadka/Ovosh";
    }

    @GetMapping("/delete/{id}")
    public String delete (@PathVariable("id") Long id, Model model) {
        gryadkaRepository.deleteById(id);
        return "redirect:/gryadka/all";
    }

    @GetMapping("/edit/{id}")
    public String edit (@PathVariable("id") Long id,
                        Model model) {
        if (!gryadkaRepository.existsById(id)) {
            return "redirect:/Ovoshi/all";
        }
        Optional<Ovoshi> user = gryadkaRepository.findById(id);
        ArrayList<Ovoshi> arrayList = new ArrayList<>();
        user.ifPresent(arrayList::add);
        model.addAttribute("Ovoshi", arrayList);
        return "gryadka/EditOvosh";
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

    @PostMapping("/edit/{id}")
    public String edit (@PathVariable("id") Long id,
                        @RequestParam("name") String name,
                        @RequestParam("color") String color,
                        @RequestParam("IQ") Integer IQ,
                        Model model) {

        Ovoshi ovosh = gryadkaRepository.findById(id).orElseThrow();

        ovosh.setName(name);
        ovosh.setColor(color);
        ovosh.setIq(IQ);

        gryadkaRepository.save(ovosh);
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
