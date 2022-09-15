package ru.spring.pr2.model;

import javax.persistence.*;

@Entity
@Table(name = "worker")
public class Worker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String surname;
    private String lastname;

    @OneToOne(optional = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "email_id")
    private Email email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public Worker(String name, String surname, String lastname, Email email) {
        this.name = name;
        this.surname = surname;
        this.lastname = lastname;
        this.email = email;
    }

    public Worker(){

    }
}
