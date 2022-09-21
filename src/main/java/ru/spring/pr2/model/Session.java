package ru.spring.pr2.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @NotNull(message = "Не должно быть пустым")
    public String datetime;

    @Min(message = "Стоимость не может быть отрицательной", value = 0)
    @NotNull(message = "Не должно быть пустым")
    public Integer cost;

    @ManyToOne(optional = false)
    public Performance performance;

    @ManyToOne(optional = false)
    public Hall hall;

    public Session(String datetime, Integer cost, Performance performance, Hall hall) {
        this.datetime = datetime;
        this.cost = cost;
        this.performance = performance;
        this.hall = hall;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Performance getPerformance() {
        return performance;
    }

    public void setPerformance(Performance performance) {
        this.performance = performance;
    }

    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    public Session() {
    }
}
