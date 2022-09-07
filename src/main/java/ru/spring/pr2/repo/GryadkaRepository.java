package ru.spring.pr2.repo;

import org.springframework.data.repository.CrudRepository;
import ru.spring.pr2.model.Ovoshi;

import java.util.List;

public interface GryadkaRepository extends CrudRepository<Ovoshi, Long> {
    public List<Ovoshi> findByNameContains(String name);
}
