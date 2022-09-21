package ru.spring.pr2.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Performance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @NotBlank(message = "Не должно быть пустым")
    public String performanceName;

    @NotNull(message = "Не должно быть пустым")
    @Min(message = "Должно быть больше 0", value = 0)
    public Integer duration;

    @ManyToOne(optional = false)
    public Genre genre;

    @ManyToOne(optional = false)
    public Director director;

    public Performance(){}

    public Performance(String performanceName, Integer duration, Genre genre, Director director) {
        this.performanceName = performanceName;
        this.duration = duration;
        this.genre = genre;
        this.director = director;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPerformanceName() {
        return performanceName;
    }

    public void setPerformanceName(String performanceName) {
        this.performanceName = performanceName;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }
}
