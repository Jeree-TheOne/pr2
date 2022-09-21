package ru.spring.pr2.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.spring.pr2.model.Hall;

public interface HallRepository extends JpaRepository<Hall, Long> {
}
