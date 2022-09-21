package ru.spring.pr2.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
public class Hall {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @NotNull(message = "Не должно быть пустым")
    @Min(message = "Должно быть больше 0", value = 0)
    public Integer hallNumber;


    @NotNull(message = "Не должно быть пустым")
    @Min(message = "Должно быть больше 0", value = 0)
    public Integer spotsAmount;


    @ManyToOne(optional = false)
    public HallType hallType;

    @ManyToOne(optional = false)
    public Theatre theatre;

    public Hall() {
    }

    public Hall(Integer hallNumber, Integer spotsAmount, HallType hallType, Theatre theatre) {
        this.hallNumber = hallNumber;
        this.spotsAmount = spotsAmount;
        this.hallType = hallType;
        this.theatre = theatre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getHallNumber() {
        return hallNumber;
    }

    public void setHallNumber(Integer hallNumber) {
        this.hallNumber = hallNumber;
    }

    public Integer getSpotsAmount() {
        return spotsAmount;
    }

    public void setSpotsAmount(Integer spotsAmount) {
        this.spotsAmount = spotsAmount;
    }

    public HallType getHallType() {
        return hallType;
    }

    public void setHallType(HallType hallType) {
        this.hallType = hallType;
    }

    public Theatre getTheatre() {
        return theatre;
    }

    public void setTheatre(Theatre theatre) {
        this.theatre = theatre;
    }
}
