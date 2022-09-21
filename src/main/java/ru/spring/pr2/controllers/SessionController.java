package ru.spring.pr2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.spring.pr2.model.Actor;
import ru.spring.pr2.model.Hall;
import ru.spring.pr2.model.Performance;
import ru.spring.pr2.model.Session;
import ru.spring.pr2.repo.ActorRepository;
import ru.spring.pr2.repo.HallRepository;
import ru.spring.pr2.repo.PerformanceRepository;
import ru.spring.pr2.repo.SessionRepository;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping("/session")
public class SessionController {

    @Autowired
    SessionRepository repository;
    String folder = "Session"; // Templates Folder
    String redirect = "redirect:/session/"; // url

    @Autowired
    PerformanceRepository performanceRepository;

    @Autowired
    HallRepository hallRepository;


    @GetMapping("/")
    public String get_all(Model model) {
        Iterable<Session> allModels = repository.findAll();
        model.addAttribute("allModels", allModels);
        model.addAttribute("pageTitle", "All " + folder);
        return folder + "/All" + folder;
    }

    @GetMapping("/{id}")
    public String get_one(@PathVariable("id") Long id, Model model) {
        Optional<Session> allModels = repository.findById(id);
        ArrayList<Session> arrayList = new ArrayList<>();
        allModels.ifPresent(arrayList::add);
        model.addAttribute("allModels", arrayList);
        return folder + "/Single" + folder;
    }

    @GetMapping("/add")
    public String add(Session session, // Object
                      Model model) {
        Iterable<Performance> p = performanceRepository.findAll();
        Iterable<Hall> h = hallRepository.findAll();

        model.addAttribute("performances", p);
        model.addAttribute("halls", h);

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
        Iterable<Hall> h = hallRepository.findAll();

        model.addAttribute("performances", p);
        model.addAttribute("halls", h);

        Optional<Session> newsList = repository.findById(id);
        ArrayList<Session> arrayList = new ArrayList<>();
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
    public String add(@Valid Session instance,
                      @RequestParam String datetime,
                      @RequestParam Integer  cost,
                      @RequestParam Long performance_id,
                      @RequestParam Long hall_id,
                      Model model) {

        Performance p = performanceRepository.findById(performance_id).orElseThrow();
        Hall h = hallRepository.findById(hall_id).orElseThrow();

        Session s = new Session(datetime, cost, p, h);

        repository.save(s);
        return redirect;
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id,
                       @RequestParam String datetime,
                       @RequestParam Integer  cost,
                       @RequestParam Long performance_id,
                       @RequestParam Long hall_id,
                       Model model) {
        if (!repository.existsById(id))
            return redirect;

        Performance p = performanceRepository.findById(performance_id).orElseThrow();
        Hall h = hallRepository.findById(hall_id).orElseThrow();
        Session s = repository.findById(id).orElseThrow();

        s.setDatetime(datetime);
        s.setCost(cost);
        s.setPerformance(p);
        s.setHall(h);

        repository.save(s);
        return redirect;
    }
}
