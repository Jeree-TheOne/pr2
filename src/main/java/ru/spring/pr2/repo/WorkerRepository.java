package ru.spring.pr2.repo;

import org.springframework.data.repository.CrudRepository;
import ru.spring.pr2.model.Worker;

public interface WorkerRepository extends CrudRepository<Worker, Integer> {
}
