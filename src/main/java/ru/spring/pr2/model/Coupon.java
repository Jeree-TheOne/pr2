package ru.spring.pr2.model;

import org.springframework.context.annotation.Bean;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;


    private String status;

    @NotNull(message = "Не должно быть пустым")
    public String activeUntil;

    @ManyToOne(optional = false)
    public User user;

    public Coupon(String status, String activeUntil, User user) {
        this.status = status;
        this.activeUntil = activeUntil;
        this.user = user;
    }

    public Coupon() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getActiveUntil() {
        return activeUntil;
    }

    public void setActiveUntil(String activeUntil) {
        this.activeUntil = activeUntil;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
