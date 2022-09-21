package ru.spring.pr2.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.spring.pr2.model.HallType;

public interface HallTypeRepository extends JpaRepository<HallType, Long> {
}