package ru.spring.pr2.repo;

import org.springframework.data.repository.CrudRepository;
import ru.spring.pr2.model.Email;

public interface EmailRepository extends CrudRepository<Email, Integer> {
    Email findByName(String name);
}
