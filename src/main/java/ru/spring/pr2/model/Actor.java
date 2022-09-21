package ru.spring.pr2.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @NotBlank(message = "Не должно быть пустым")
    public String role;

    @Size(message = "Имя должно быть от 5 до 100 символов", min = 5, max = 100)
    @NotBlank(message = "Не должно быть пустым")
    public String actorName;

    @ManyToOne(optional = false)
    public Performance performance;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getActorName() {
        return actorName;
    }

    public void setActorName(String actorName) {
        this.actorName = actorName;
    }

    public Performance getPerformance() {
        return performance;
    }

    public void setPerformance(Performance performance) {
        this.performance = performance;
    }

    public Actor() {
    }

    public Actor(String role, String actorName, Performance performance) {
        this.role = role;
        this.actorName = actorName;
        this.performance = performance;
    }
}
