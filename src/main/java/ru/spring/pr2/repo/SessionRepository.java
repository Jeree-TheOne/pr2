package ru.spring.pr2.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.spring.pr2.model.Session;

public interface SessionRepository extends JpaRepository<Session, Long> {
}