package ru.spring.pr2.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class mpt {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;
    @Column
    private int course;
    @ManyToMany
    @JoinTable(name="student_mpt",
            joinColumns=@JoinColumn(name="mpt_id"),
            inverseJoinColumns=@JoinColumn(name="student_id"))
    private List<student> students;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public List<student> getStudents() {
        return students;
    }

    public void setStudents(List<student> students) {
        this.students = students;
    }
}
