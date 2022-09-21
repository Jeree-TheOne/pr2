package ru.spring.pr2.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class HallType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @NotBlank(message = "Не должно быть пустым")
    public String hallTypeName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHallTypeName() {
        return hallTypeName;
    }

    public void setHallTypeName(String hallTypeName) {
        this.hallTypeName = hallTypeName;
    }
}