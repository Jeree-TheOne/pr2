package ru.spring.pr2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.spring.pr2.model.Chel;
import ru.spring.pr2.model.Ovoshi;
import ru.spring.pr2.repo.ChelRepository;

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
                      @RequestParam(value = "isZoomer", defaultValue = "0") Boolean isZoomer,
                      Model model) {

        Chel newUser = new Chel(name, nickname, gender, age, isZoomer);
        chelRepository.save(newUser);
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
    public String edit (@PathVariable("id") Long id,
                        Model model) {
        if (!chelRepository.existsById(id)) {
            return "redirect:/chel/all";
        }
        Optional<Chel> user = chelRepository.findById(id);
        ArrayList<Chel> arrayList = new ArrayList<>();
        user.ifPresent(arrayList::add);
        model.addAttribute("Chel", arrayList);
        return "chels/EditChel";
    }

    @PostMapping("/edit/{id}")
    public String edit (@PathVariable("id") Long id,
                        @RequestParam("name") String name,
                        @RequestParam("nickname") String nickname,
                        @RequestParam("gender") String gender,
                        @RequestParam("age") Integer age,
                        @RequestParam(value = "isZoomer", defaultValue = "0") Boolean isZoomer,
                        Model model) {

        Chel chel = chelRepository.findById(id).orElseThrow();

        chel.setName(name);
        chel.setNickname(nickname);
        chel.setGender(gender);
        chel.setAge(age);
        chel.setIsZoomer(isZoomer);

        chelRepository.save(chel);
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
