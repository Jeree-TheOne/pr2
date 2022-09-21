package ru.spring.pr2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.spring.pr2.model.Director;
import ru.spring.pr2.model.Genre;
import ru.spring.pr2.model.Performance;
import ru.spring.pr2.repo.DirectorRepository;
import ru.spring.pr2.repo.GenreRepository;
import ru.spring.pr2.repo.PerformanceRepository;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping("/performance")
public class PerformanceController {

    @Autowired
    PerformanceRepository repository;
    String folder = "Performance"; // Templates Folder
    String redirect = "redirect:/performance/"; // url

    @Autowired
    DirectorRepository directorRepository;

    @Autowired
    GenreRepository genreRepository;


    @GetMapping("/")
    public String get_all(Model model) {
        Iterable<Performance> allModels = repository.findAll();
        model.addAttribute("allModels", allModels);
        model.addAttribute("pageTitle", "All " + folder);
        return folder + "/All" + folder;
    }

    @GetMapping("/{id}")
    public String get_one(@PathVariable("id") Long id, Model model) {
        Optional<Performance> allModels = repository.findById(id);
        ArrayList<Performance> arrayList = new ArrayList<>();
        allModels.ifPresent(arrayList::add);
        model.addAttribute("allModels", arrayList);
        return folder + "/Single" + folder;
    }

    @GetMapping("/add")
    public String add(Performance performance, // Object
                      Model model) {
        Iterable<Director> d = directorRepository.findAll();
        Iterable<Genre> g = genreRepository.findAll();

        model.addAttribute("directors", d);
        model.addAttribute("genres", g);

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

        Iterable<Director> d = directorRepository.findAll();
        Iterable<Genre> g = genreRepository.findAll();

        model.addAttribute("directors", d);
        model.addAttribute("genres", g);

        Optional<Performance> newsList = repository.findById(id);
        ArrayList<Performance> arrayList = new ArrayList<>();
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
    public String add(@Valid Performance instance,
                      @RequestParam String performanceName,
                      @RequestParam Integer duration,
                      @RequestParam Long genre_id,
                      @RequestParam Long director_id,
                      Model model) {

        Genre g = genreRepository.findById(genre_id).orElseThrow();
        Director d = directorRepository.findById(director_id).orElseThrow();

        Performance p = new Performance(performanceName, duration, g, d);

        repository.save(p);
        return redirect;
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id,
                       @RequestParam String performanceName,
                       @RequestParam Integer duration,
                       @RequestParam Long genre_id,
                       @RequestParam Long director_id,
                       Model model) {
        if (!repository.existsById(id))
            return redirect;

        Performance p = repository.findById(id).orElseThrow();
        Genre g = genreRepository.findById(genre_id).orElseThrow();
        Director d = directorRepository.findById(director_id).orElseThrow();

        p.setDirector(d);
        p.setDuration(duration);
        p.setPerformanceName(performanceName);
        p.setGenre(g);

        repository.save(p);
        return redirect;
    }
}
