package com.group3.wineshop.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name ="type")
public class Type {
    @Id
    private Long id;
    private String name;

    @OneToMany(mappedBy = "type")
    private Set<Object> wines = new java.util.LinkedHashSet<>();

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

    public Set<Wine> getWines() {
        this.name = name;
    }

    public void setWines(Set<Wine>wines) {
        this.wines = wines;
    }

    @Override
    public String toString() {
        return "Type{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
