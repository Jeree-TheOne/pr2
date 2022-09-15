package ru.spring.pr2.repo;

import org.springframework.data.repository.CrudRepository;
import ru.spring.pr2.model.student;

public interface StudentRepository extends CrudRepository<student, Integer> {
    student findByPosition(String Position);
}
