package ru.spring.pr2.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.spring.pr2.model.Performance;

public interface PerformanceRepository extends JpaRepository<Performance, Long> {
}
