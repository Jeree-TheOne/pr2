package ru.spring.pr2.repo;

import org.springframework.data.repository.CrudRepository;
import ru.spring.pr2.model.News;

import java.util.List;

public interface NewsRepository extends CrudRepository<News, Long> {
    public List<News> findByTitle(String title);
    public List<News> findByTitleContains(String title);
}
