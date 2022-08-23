package com.group3.wineshop.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "winery")
public class Winery {

    private Long id;
    private String name;

    @OneToMany(mappedBy = "winery")
    private Set<Object> wines;

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    public Long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Set<Object> getWines() {
        return wines;
    }

    public void setWines(Set<Object> wines) {
        this.wines = wines;
    }

    @Override
    public String toString() {
        return "Winery{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", wines=" + wines +
                '}';
    }
}
