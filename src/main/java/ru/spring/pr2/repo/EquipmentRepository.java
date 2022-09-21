package ru.spring.pr2.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.spring.pr2.model.Equipment;

public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
}
