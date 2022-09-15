package ru.spring.pr2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.spring.pr2.model.Email;
import ru.spring.pr2.model.Worker;
import ru.spring.pr2.repo.EmailRepository;
import ru.spring.pr2.repo.WorkerRepository;

@Controller
public class EmailController {
    @Autowired
    private EmailRepository emailRepository;
    @Autowired
    private WorkerRepository workerRepository;

    @GetMapping("/email")
    public String Main(Model model){
        Iterable<Email> email = emailRepository.findAll();
        model.addAttribute("email", email);
        return "email";
    }

    @PostMapping("/email/add")
    public String blogPostAdd(@RequestParam String name, @RequestParam String surname, @RequestParam String lastname, @RequestParam String email_name, Model model)
    {
        System.out.println(name);
        Email email = emailRepository.findByName(email_name);
        System.out.println(email.getId());
        Worker worker = new Worker(name,surname, lastname, email);
        workerRepository.save(worker);
        return "redirect:/email";
    }
}
