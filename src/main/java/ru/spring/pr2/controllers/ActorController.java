package ru.spring.pr2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.spring.pr2.model.Actor;
import ru.spring.pr2.model.Director;
import ru.spring.pr2.model.Genre;
import ru.spring.pr2.model.Performance;
import ru.spring.pr2.repo.ActorRepository;
import ru.spring.pr2.repo.DirectorRepository;
import ru.spring.pr2.repo.GenreRepository;
import ru.spring.pr2.repo.PerformanceRepository;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping("/actor")
public class ActorController {

    @Autowired
    ActorRepository repository;
    String folder = "Actor"; // Templates Folder
    String redirect = "redirect:/actor/"; // url

    @Autowired
    PerformanceRepository performanceRepository;


    @GetMapping("/")
    public String get_all(Model model) {
        Iterable<Actor> allModels = repository.findAll();
        model.addAttribute("allModels", allModels);
        model.addAttribute("pageTitle", "All " + folder);
        return folder + "/All" + folder;
    }

    @GetMapping("/{id}")
    public String get_one(@PathVariable("id") Long id, Model model) {
        Optional<Actor> allModels = repository.findById(id);
        ArrayList<Actor> arrayList = new ArrayList<>();
        allModels.ifPresent(arrayList::add);
        model.addAttribute("allModels", arrayList);
        return folder + "/Single" + folder;
    }

    @GetMapping("/add")
    public String add(Actor actor, // Object
                      Model model) {
        Iterable<Performance> p = performanceRepository.findAll();

        model.addAttribute("performances", p);

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

        Iterable<Performance> p = performanceRepository.findAll();

        model.addAttribute("performances", p);

        Optional<Actor> newsList = repository.findById(id);
        ArrayList<Actor> arrayList = new ArrayList<>();
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
    public String add(@Valid Actor instance,
                      @RequestParam String role,
                      @RequestParam String  actorName,
                      @RequestParam Long performance_id,
                      Model model) {

        Performance p = performanceRepository.findById(performance_id).orElseThrow();

        Actor a = new Actor(role, actorName, p);

        repository.save(a);
        return redirect;
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id,
                       @RequestParam String actorName,
                       @RequestParam String role,
                       @RequestParam Long performance_id,
                       Model model) {
        if (!repository.existsById(id))
            return redirect;

        Performance p = performanceRepository.findById(performance_id).orElseThrow();
        Actor a = repository.findById(id).orElseThrow();

        a.setActorName(actorName);
        a.setPerformance(p);
        a.setRole(role);

        repository.save(a);
        return redirect;
    }
}
