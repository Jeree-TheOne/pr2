package ru.spring.pr2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.spring.pr2.model.Actor;
import ru.spring.pr2.model.Equipment;
import ru.spring.pr2.model.Performance;
import ru.spring.pr2.repo.ActorRepository;
import ru.spring.pr2.repo.EquipmentRepository;
import ru.spring.pr2.repo.PerformanceRepository;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping("/equipment")
public class EquipmentController {

    @Autowired
    EquipmentRepository repository;
    String folder = "Equipment"; // Templates Folder
    String redirect = "redirect:/equipment/"; // url

    @Autowired
    PerformanceRepository performanceRepository;


    @GetMapping("/")
    public String get_all(Model model) {
        Iterable<Equipment> allModels = repository.findAll();
        model.addAttribute("allModels", allModels);
        model.addAttribute("pageTitle", "All " + folder);
        return folder + "/All" + folder;
    }

    @GetMapping("/{id}")
    public String get_one(@PathVariable("id") Long id, Model model) {
        Optional<Equipment> allModels = repository.findById(id);
        ArrayList<Equipment> arrayList = new ArrayList<>();
        allModels.ifPresent(arrayList::add);
        model.addAttribute("allModels", arrayList);
        return folder + "/Single" + folder;
    }

    @GetMapping("/add")
    public String add(Equipment equipment, // Object
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

        Optional<Equipment> newsList = repository.findById(id);
        ArrayList<Equipment> arrayList = new ArrayList<>();
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
    public String add(@Valid Equipment instance,
                      @RequestParam String equipmentName,
                      @RequestParam Long performance_id,
                      Model model) {

        Performance p = performanceRepository.findById(performance_id).orElseThrow();

        Equipment e = new Equipment(equipmentName, p);

        repository.save(e);
        return redirect;
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id,
                       @RequestParam String equipmentName,
                       @RequestParam Long performance_id,
                       Model model) {
        if (!repository.existsById(id))
            return redirect;

        Performance p = performanceRepository.findById(performance_id).orElseThrow();
        Equipment e = repository.findById(id).orElseThrow();

        e.setEquipmentName(equipmentName);
        e.setPerformance(p);

        repository.save(e);
        return redirect;
    }
}
