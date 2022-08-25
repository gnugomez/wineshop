package com.group3.wineshop.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.group3.wineshop.utilities.ValidationErr;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "wine")
public class Wine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "Name is mandatory")
    @JsonProperty(value = "name", required = true)
    private String name;
    private Integer year;
    private Double rating;
    private Integer num_reviews;
    private Double price;
    @Min(value = 1, message = ValidationErr.bodyInterval)
    @Max(value = 5, message = ValidationErr.bodyInterval)
    private Integer body;
    @Min(value = 1, message = ValidationErr.acidityInterval)
    @Max(value = 5, message = ValidationErr.acidityInterval)
    private Integer acidity;
    private Integer winery_id;
    private Integer type_id;
    private Integer region_id;

    public Wine(){

    }

    public Wine(Integer id, String name, Integer year, Double rating, Integer num_reviews, Double price,
                Integer body, Integer acidity, Integer winery_id, Integer type_id, Integer region_id) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.rating = rating;
        this.num_reviews = num_reviews;
        this.price = price;
        this.body = body;
        this.acidity = acidity;
        this.winery_id = winery_id;
        this.type_id = type_id;
        this.region_id = region_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Integer getNum_reviews() {
        return num_reviews;
    }

    public void setNum_reviews(Integer num_reviews) {
        this.num_reviews = num_reviews;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getBody() {
        return body;
    }

    public void setBody(Integer body) {
        this.body = body;
    }

    public Integer getAcidity() {
        return acidity;
    }

    public void setAcidity(Integer acidity) {
        this.acidity = acidity;
    }

    public Integer getWinery_id() {
        return winery_id;
    }

    public void setWinery_id(Integer winery_id) {
        this.winery_id = winery_id;
    }

    public Integer getType_id() {
        return type_id;
    }

    public void setType_id(Integer type_id) {
        this.type_id = type_id;
    }

    public Integer getRegion_id() {
        return region_id;
    }

    public void setRegion_id(Integer region_id) {
        this.region_id = region_id;
    }

    @Override
    public String toString() {
        return "Wine{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", year=" + year +
                ", winery_id=" + winery_id +
                ", type_id=" + type_id +
                ", region_id=" + region_id +
                '}';
    }
}
