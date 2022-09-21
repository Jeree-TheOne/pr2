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
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    TicketRepository repository;
    String folder = "Ticket"; // Templates Folder
    String redirect = "redirect:/ticket/"; // url

    @Autowired
    SessionRepository sessionRepository;

    @Autowired
    UserRepository userRepository;


    @GetMapping("/")
    public String get_all(Model model) {
        Iterable<Ticket> allModels = repository.findAll();
        model.addAttribute("allModels", allModels);
        model.addAttribute("pageTitle", "All " + folder);
        return folder + "/All" + folder;
    }

    @GetMapping("/{id}")
    public String get_one(@PathVariable("id") Long id, Model model) {
        Optional<Ticket> allModels = repository.findById(id);
        ArrayList<Ticket> arrayList = new ArrayList<>();
        allModels.ifPresent(arrayList::add);
        model.addAttribute("allModels", arrayList);
        return folder + "/Single" + folder;
    }

    @GetMapping("/add")
    public String add(Ticket ticket, // Object
                      Model model) {
        Iterable<Session> s = sessionRepository.findAll();
        Iterable<User> u = userRepository.findAll();

        model.addAttribute("users", u);
        model.addAttribute("sessions", s);

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

        Iterable<Session> s = sessionRepository.findAll();
        Iterable<User> u = userRepository.findAll();

        model.addAttribute("users", u);
        model.addAttribute("sessions", s);

        Optional<Ticket> newsList = repository.findById(id);
        ArrayList<Ticket> arrayList = new ArrayList<>();
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
    public String add(@Valid Ticket instance,
                      @RequestParam Integer row,
                      @RequestParam Integer spot,
                      @RequestParam Long session_id,
                      @RequestParam Long user_id,
                      Model model) {

        Session s = sessionRepository.findById(session_id).orElseThrow();
        User u = userRepository.findById(user_id).orElseThrow();

        Ticket t = new Ticket(row, spot, u, s);

        repository.save(t);
        return redirect;
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id,
                       @RequestParam Integer row,
                       @RequestParam Integer spot,
                       @RequestParam Long session_id,
                       @RequestParam Long user_id,
                       Model model) {
        if (!repository.existsById(id))
            return redirect;

        Ticket t = repository.findById(id).orElseThrow();
        Session s = sessionRepository.findById(session_id).orElseThrow();
        User u = userRepository.findById(user_id).orElseThrow();

        t.setSession(s);
        t.setUser(u);
        t.setRow(row);
        t.setSpot(spot);

        repository.save(t);
        return redirect;
    }
}
