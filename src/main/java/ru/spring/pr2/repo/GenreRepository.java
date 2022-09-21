package ru.spring.pr2.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.spring.pr2.model.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}