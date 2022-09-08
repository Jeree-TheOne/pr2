package ru.spring.pr2.model;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Ovoshi {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String name, color;
    Integer IQ;

    public Ovoshi(String name, String color, Integer iq) {
        this.name = name;
        this.color = color;
        this.IQ = iq;
    }

    public Ovoshi() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getIQ() {
        return this.IQ;
    }

    public void setIq(Integer iq) {
        this.IQ = iq;
    }
}
