package ru.spring.pr2.repo;

import org.springframework.data.repository.CrudRepository;
import ru.spring.pr2.model.mpt;

public interface MptRepository extends CrudRepository<mpt, Integer> {
    mpt findByCourse(int course);
}
