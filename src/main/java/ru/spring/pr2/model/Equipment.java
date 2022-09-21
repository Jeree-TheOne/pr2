package ru.spring.pr2.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Equipment {
    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public Performance getPerformance() {
        return performance;
    }

    public void setPerformance(Performance performance) {
        this.performance = performance;
    }

    public Equipment() {
    }

    public Equipment(String equipmentName, Performance performance) {
        this.equipmentName = equipmentName;
        this.performance = performance;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @Size(message = "Имя должно быть от 5 до 100 символов", min = 5, max = 100)
    @NotBlank(message = "Не должно быть пустым")
    public String equipmentName;

    @ManyToOne(optional = false)
    public Performance performance;
}
