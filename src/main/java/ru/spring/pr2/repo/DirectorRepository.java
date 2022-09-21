package ru.spring.pr2.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.spring.pr2.model.Director;

public interface DirectorRepository extends JpaRepository<Director, Long> {
}