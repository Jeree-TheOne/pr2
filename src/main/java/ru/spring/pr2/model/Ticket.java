package ru.spring.pr2.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public Integer getSpot() {
        return spot;
    }

    public void setSpot(Integer spot) {
        this.spot = spot;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Ticket() {
    }

    public Ticket(Integer row, Integer spot, User user, Session session) {
        this.row = row;
        this.spot = spot;
        this.user = user;
        this.session = session;
    }

    @NotNull(message = "Не должно быть пустым")
    @Min(message = "Должно быть больше 0", value = 0)
    public Integer row;

    @NotNull(message = "Не должно быть пустым")
    @Min(message = "Должно быть больше 0", value = 0)
    public Integer spot;

    @ManyToOne(optional = false)
    public User user;

    @ManyToOne(optional = false)
    public Session session;
}
