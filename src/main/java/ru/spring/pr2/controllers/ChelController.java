package ru.spring.pr2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.spring.pr2.model.Chel;
import ru.spring.pr2.model.Ovoshi;
import ru.spring.pr2.repo.ChelRepository;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public String reg(Chel chel, Model model) {
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
    public String add(@Valid Chel newChel,
                      BindingResult bindingResult,
                      Model model) {

        if (bindingResult.hasErrors())
            return "chels/Registration";
        chelRepository.save(newChel);
        return "redirect:/chel/all";
    }

    @GetMapping("/{id}")
    public String read (@PathVariable("id") Long id, Model model) {
        Optional<Chel> Chels = chelRepository.findById(id);
        ArrayList<Chel> arrayList = new ArrayList<>();
        Chels.ifPresent(arrayList::add);
        model.addAttribute("Chel", arrayList);
        return "chels/Chel";
    }

    @GetMapping("/delete/{id}")
    public String delete (@PathVariable("id") Long id, Model model) {
        chelRepository.deleteById(id);
        return "redirect:/chel/all";
    }

    @GetMapping("/edit/{id}")
    public String edit(
            @PathVariable("id") Long id,
            Model model)
    {
        if(!chelRepository.existsById(id))
        {
            return "redirect:/films/";
        }
        Optional<Chel> newsList = chelRepository.findById(id);
        ArrayList<Chel> arrayList = new ArrayList<>();
        newsList.ifPresent(arrayList::add);
        model.addAttribute("Chel", arrayList.get(0));
        return "chels/EditChel";
    }

    @PostMapping("/edit/{id}")
    public String edit (@PathVariable("id") Long id,
                        @ModelAttribute("Chel") @Valid Chel newChel, BindingResult bindingResult) {
        if (!chelRepository.existsById(id))
            return "redirect:/chel/all";
        if (bindingResult.hasErrors())
            return "chels/EditChel";

        newChel.setId(id);
        chelRepository.save(newChel);
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
