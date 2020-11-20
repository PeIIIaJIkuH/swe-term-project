package com.hotel.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class RoomTypeFeature {
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer price;

    public RoomTypeFeature() {
    }

    public RoomTypeFeature(String name, Integer price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
