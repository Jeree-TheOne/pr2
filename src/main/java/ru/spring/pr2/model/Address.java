package ru.spring.pr2.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String city;
    private String street;
    private String building;
    @OneToMany(mappedBy = "address", fetch = FetchType.EAGER)
    private Collection<Shop> shops;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public Collection<Shop> getShops() {
        return shops;
    }

    public void setShops(Collection<Shop> shops) {
        this.shops = shops;
    }
}
