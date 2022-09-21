package ru.spring.pr2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.spring.pr2.model.Theatre;
import ru.spring.pr2.repo.TheatreRepository;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping("/theatre")
public class TheatreController {

    @Autowired
    TheatreRepository repository;
    String folder = "Theatre"; // Templates Folder
    String redirect = "redirect:/theatre/"; // url

    @GetMapping("/")
    public String get_all(Model model) {
        Iterable<Theatre> allModels = repository.findAll();
        model.addAttribute("allModels", allModels);
        model.addAttribute("pageTitle", "All " + folder);
        return folder + "/All" + folder;
    }

    @GetMapping("/{id}")
    public String get_one(@PathVariable("id") Long id, Model model) {
        Optional<Theatre> allModels = repository.findById(id);
        ArrayList<Theatre> arrayList = new ArrayList<>();
        allModels.ifPresent(arrayList::add);
        model.addAttribute("allModels", arrayList);
        return folder + "/Single" + folder;
    }

    @GetMapping("/add")
    public String add(Theatre theatre, // Object
                      Model model) {
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
        Optional<Theatre> newsList = repository.findById(id);
        ArrayList<Theatre> arrayList = new ArrayList<>();
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
    public String add(@Valid Theatre instance,
                      BindingResult bindingResult,
                      Model model) {
        if (bindingResult.hasErrors())
            return folder + "/Add" + folder;
        repository.save(instance);
        return redirect;
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id,
                       @ModelAttribute("allModels") @Valid Theatre instance, BindingResult bindingResult) {
        if (!repository.existsById(id))
            return redirect;
        if (bindingResult.hasErrors())
            return folder + "/Edit" + folder;

        instance.setId(id);
        repository.save(instance);
        return redirect;
    }
}