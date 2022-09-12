package ru.spring.pr2.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;

@Entity
public class Chel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @NotBlank(message = "Поле не должно быть пустым")
    @Size(message = "Lyaaa chel", min = 5, max = 100)
    String name;

    @NotBlank(message = "Поле не должно быть пустым")
    @Size(message = "Lyaaa chel", min = 5, max = 100)
    String nickname;

    @NotBlank(message = "Поле не должно быть пустым")
    @Size(message = "Lyaaa chel", min = 5, max = 100)
    String gender;

    @NotNull(message = "Поле не должно быть пустым")
    @Min(message = "Lyaaa chel", value = 0)
    @Max(message = "Lyaaa chel", value = 100)
    Integer age;

    Boolean isZoomer;


    public Chel(String name, String nickname, String gender, Integer age, Boolean isZoomer) {
        this.name = name;
        this.nickname = nickname;
        this.gender = gender;
        this.age = age;
        this.isZoomer = isZoomer;
    }

    public Chel() {
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean getIsZoomer() {
        return this.isZoomer;
    }

    public void setIsZoomer(Boolean isZoomer) {
        this.isZoomer = isZoomer;
    }
}
