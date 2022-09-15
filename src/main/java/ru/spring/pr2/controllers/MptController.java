package ru.spring.pr2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.spring.pr2.model.mpt;
import ru.spring.pr2.model.student;
import ru.spring.pr2.repo.MptRepository;
import ru.spring.pr2.repo.StudentRepository;

@Controller
public class MptController {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private MptRepository mptRepository;

    @GetMapping("/student")
    private String stumpt(Model model){
        Iterable<student> students = studentRepository.findAll();
        model.addAttribute("students", students);
        Iterable<mpt> universities = mptRepository.findAll();
        model.addAttribute("mpt", universities);

        return "student";
    }
    @PostMapping("/student/add")
    public String blogPostAdd(@RequestParam String student, @RequestParam int mpt, Model model)
    {
        student student2 = studentRepository.findByPosition(student);
        mpt university2 = mptRepository.findByCourse(mpt);
        student2.getMpt().add(university2);
        university2.getStudents().add(student2);
        studentRepository.save(student2);
        mptRepository.save(university2);
        return "redirect:/student";
    }
}
