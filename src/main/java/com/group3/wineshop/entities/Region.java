package com.group3.wineshop.entities;


import javax.persistence.*;
import java.util.LinkedHashSet;

@Entity
@Table(name="region")
public class Region {
    @OneToMany(mappedBy="region")
    private Set<Wine> wines = new LinkedHashSet<>();

    @Id
    @GeneratedValue
    private long id;

    private String name;
    private String country;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Region{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                '}';
    }

    public Region(int id, String name, String country) {
        this.id = id;
        this.name = name;
        this.country = country;
    }
}
