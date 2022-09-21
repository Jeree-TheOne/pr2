package ru.spring.pr2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.spring.pr2.model.*;
import ru.spring.pr2.repo.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping("/hall")
public class HallController {

    @Autowired
    HallRepository repository;
    String folder = "Hall"; // Templates Folder
    String redirect = "redirect:/hall/"; // url

    @Autowired
    HallTypeRepository hallTypeRepository;

    @Autowired
    TheatreRepository theatreRepository;


    @GetMapping("/")
    public String get_all(Model model) {
        Iterable<Hall> allModels = repository.findAll();
        model.addAttribute("allModels", allModels);
        model.addAttribute("pageTitle", "All " + folder);
        return folder + "/All" + folder;
    }

    @GetMapping("/{id}")
    public String get_one(@PathVariable("id") Long id, Model model) {
        Optional<Hall> allModels = repository.findById(id);
        ArrayList<Hall> arrayList = new ArrayList<>();
        allModels.ifPresent(arrayList::add);
        model.addAttribute("allModels", arrayList);
        return folder + "/Single" + folder;
    }

    @GetMapping("/add")
    public String add(Hall hall, // Object
                      Model model) {
        Iterable<HallType> ht = hallTypeRepository.findAll();
        Iterable<Theatre> t = theatreRepository.findAll();

        model.addAttribute("hallTypes", ht);
        model.addAttribute("theatres", t);

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

        Iterable<HallType> ht = hallTypeRepository.findAll();
        Iterable<Theatre> t = theatreRepository.findAll();

        model.addAttribute("hallTypes", ht);
        model.addAttribute("theatres", t);

        Optional<Hall> newsList = repository.findById(id);
        ArrayList<Hall> arrayList = new ArrayList<>();
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
    public String add(@Valid Hall instance,
                      @RequestParam Integer hallNumber,
                      @RequestParam Integer spotsAmount,
                      @RequestParam Long hall_type_id,
                      @RequestParam Long theatre_id,
                      Model model) {

        HallType ht = hallTypeRepository.findById(hall_type_id).orElseThrow();
        Theatre t = theatreRepository.findById(theatre_id).orElseThrow();

        Hall h = new Hall(hallNumber, spotsAmount, ht, t);

        repository.save(h);
        return redirect;
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id,
                       @RequestParam Integer hallNumber,
                       @RequestParam Integer spotsAmount,
                       @RequestParam Long hall_type_id,
                       @RequestParam Long theatre_id,
                       Model model) {
        if (!repository.existsById(id))
            return redirect;

        HallType ht = hallTypeRepository.findById(hall_type_id).orElseThrow();
        Theatre t = theatreRepository.findById(theatre_id).orElseThrow();
        Hall h = repository.findById(id).orElseThrow();

        h.setHallType(ht);
        h.setTheatre(t);
        h.setHallNumber(hallNumber);
        h.setSpotsAmount(spotsAmount);

        repository.save(h);
        return redirect;
    }
}
