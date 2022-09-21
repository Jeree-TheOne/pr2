package ru.spring.pr2.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.spring.pr2.model.Actor;

public interface ActorRepository extends JpaRepository<Actor, Long> {
}
