package ru.spring.pr2.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.spring.pr2.model.Theatre;

public interface TheatreRepository extends JpaRepository<Theatre, Long> {
}
