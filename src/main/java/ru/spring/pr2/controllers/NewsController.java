package ru.spring.pr2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.spring.pr2.model.News;
import ru.spring.pr2.repo.NewsRepository;

import java.util.List;

@Controller
@RequestMapping("/news")
public class NewsController {

    @Autowired
    private NewsRepository newsRepository;

    @GetMapping("/")
    public String index(Model model) {

        Iterable<News> news = newsRepository.findAll();
        model.addAttribute("news", news);
        return "news/News";
    }

    @GetMapping("/add")
    public String addView(Model model) {
        return "news/Add";
    }

    @PostMapping("/add")
    public String add(@RequestParam("title") String title,
                      @RequestParam("text") String text,
                      @RequestParam("author") String author,
                      @RequestParam("views") Integer views,
                      @RequestParam("likes") Integer likes,
                      Model model) {

        News newsOne = new News(title, text, author, views, likes);
        newsRepository.save(newsOne);
        return "redirect:/news/";
    }

    @GetMapping("/search")
    public String search(@RequestParam("title") String title,
                      Model model) {
        List<News> newsList = newsRepository.findByTitleContains(title);
        model.addAttribute("news", newsList);
        return "news/News";
    }
}
