package com.hotel.payload.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

public class RoomTypeRequest {
    @NotBlank
    private String name;

    @NotNull
    private Integer size;

    @NotNull
    private Integer capacity;

    @NotNull
    private Integer price;

    @NotNull
    private Set<RoomRequest> rooms = new HashSet<>();

    private Set<RoomTypeFeatureRequest> features = new HashSet<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Set<RoomTypeFeatureRequest> getFeatures() {
        return features;
    }

    public void setFeatures(Set<RoomTypeFeatureRequest> features) {
        this.features = features;
    }

    public Set<RoomRequest> getRooms() {
        return rooms;
    }

    public void setRooms(Set<RoomRequest> rooms) {
        this.rooms = rooms;
    }
}
