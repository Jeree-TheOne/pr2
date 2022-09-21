package ru.spring.pr2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.spring.pr2.model.*;
import ru.spring.pr2.repo.ActorRepository;
import ru.spring.pr2.repo.CouponRepository;
import ru.spring.pr2.repo.PerformanceRepository;
import ru.spring.pr2.repo.UserRepository;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping("/coupon")
public class CouponController {

    @Autowired
    CouponRepository repository;
    String folder = "Coupon"; // Templates Folder
    String redirect = "redirect:/coupon/"; // url

    @Autowired
    UserRepository userRepository;


    @GetMapping("/")
    public String get_all(Model model) {
        Iterable<Coupon> allModels = repository.findAll();
        model.addAttribute("allModels", allModels);
        model.addAttribute("pageTitle", "All " + folder);
        return folder + "/All" + folder;
    }

    @GetMapping("/{id}")
    public String get_one(@PathVariable("id") Long id, Model model) {
        Optional<Coupon> allModels = repository.findById(id);
        ArrayList<Coupon> arrayList = new ArrayList<>();
        allModels.ifPresent(arrayList::add);
        model.addAttribute("allModels", arrayList);
        return folder + "/Single" + folder;
    }

    @GetMapping("/add")
    public String add(Coupon coupon, // Object
                      Model model) {
        Iterable<User> u = userRepository.findAll();

        model.addAttribute("users", u);

        return folder + "/Add" + folder; //Путь к файлу
    }

    @GetMapping("/edit/{id}")
    public String edit(
            @PathVariable("id") Long id,
            Model model)
    {
        if(!repository.existsById(id))
        {
            return redirect;
        }

        Iterable<User> u = userRepository.findAll();

        model.addAttribute("users", u);

        Optional<Coupon> newsList = repository.findById(id);
        ArrayList<Coupon> arrayList = new ArrayList<>();
        newsList.ifPresent(arrayList::add);
        model.addAttribute("allModels", arrayList.get(0));
        return folder + "/Edit" + folder;
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id, Model model) {
        repository.deleteById(id);
        return redirect;
    }

    @PostMapping("/add")
    public String add(@Valid Coupon instance,
                      @RequestParam String  activeUntil,
                      @RequestParam Long user_id,
                      Model model) {

        User u = userRepository.findById(user_id).orElseThrow();

        Coupon c = new Coupon("ACTIVE", activeUntil, u);

        repository.save(c);
        return redirect;
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id,
                       @RequestParam String status,
                       @RequestParam String  activeUntil,
                       @RequestParam Long user_id,
                       Model model) {
        if (!repository.existsById(id))
            return redirect;

        User u = userRepository.findById(user_id).orElseThrow();
        Coupon c = repository.findById(id).orElseThrow();

        c.setActiveUntil(activeUntil);
        c.setStatus(status);
        c.setUser(u);

        repository.save(c);
        return redirect;
    }
}
