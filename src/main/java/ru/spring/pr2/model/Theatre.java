package ru.spring.pr2.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Theatre {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @NotBlank(message = "Не должно быть пустым")
    public String address;

    @NotNull(message = "Не должно быть пустым")
    @Min(message = "Не может быть меньше 0", value = 0)
    public Integer hallsAmount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getHallsAmount() {
        return hallsAmount;
    }

    public void setHallsAmount(Integer hallsAmount) {
        this.hallsAmount = hallsAmount;
    }
}
