package ru.spring.pr2.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Id;

    private String position;
    @ManyToMany
    @JoinTable(name="student_mpt",
            joinColumns=@JoinColumn(name="student_id"),
            inverseJoinColumns=@JoinColumn(name="mpt_id"))
    private List<mpt> mpt;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public List<mpt> getMpt() {
        return mpt;
    }

    public void setMpt(List<mpt> mpt) {
        this.mpt = mpt;
    }

    public student(String position, List<mpt> mpg) {
        this.position = position;
        this.mpt = mpg;
    }

    public student() {
    }
}
