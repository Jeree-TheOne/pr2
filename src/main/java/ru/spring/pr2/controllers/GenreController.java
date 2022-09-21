package ru.spring.pr2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.spring.pr2.model.Genre;
import ru.spring.pr2.repo.GenreRepository;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping("/genre")
public class GenreController {

    @Autowired
    GenreRepository repository;
    String folder = "Genre"; // Templates Folder
    String redirect = "redirect:/genre/"; // url

    @GetMapping("/")
    public String get_all(Model model) {
        Iterable<Genre> allModels = repository.findAll();
        model.addAttribute("allModels", allModels);
        model.addAttribute("pageTitle", "All " + folder);
        return folder + "/All" + folder;
    }

    @GetMapping("/{id}")
    public String get_one(@PathVariable("id") Long id, Model model) {
        Optional<Genre> allModels = repository.findById(id);
        ArrayList<Genre> arrayList = new ArrayList<>();
        allModels.ifPresent(arrayList::add);
        model.addAttribute("allModels", arrayList);
        return folder + "/Single" + folder;
    }

    @GetMapping("/add")
    public String add(Genre genre, // Object
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
        Optional<Genre> newsList = repository.findById(id);
        ArrayList<Genre> arrayList = new ArrayList<>();
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
    public String add(@Valid Genre instance,
                      BindingResult bindingResult,
                      Model model) {
        if (bindingResult.hasErrors())
            return folder + "/Add" + folder;
        repository.save(instance);
        return redirect;
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id,
                       @ModelAttribute("allModels") @Valid Genre instance, BindingResult bindingResult) {
        if (!repository.existsById(id))
            return redirect;
        if (bindingResult.hasErrors())
            return folder + "/Edit" + folder;

        instance.setId(id);
        repository.save(instance);
        return redirect;
    }
}
