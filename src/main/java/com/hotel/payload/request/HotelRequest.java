package com.hotel.payload.request;

import javax.validation.constraints.NotNull;
import java.util.Set;

public class HotelRequest {
    @NotNull
    private String name;

    @NotNull
    private String country;

    @NotNull
    private String city;

    @NotNull
    private String street;

    @NotNull
    private Set<String> numbers;

    private Set<String> features;

    @NotNull
    private Set<String> seasons;

    private Set<RoomTypeRequest> roomTypes;

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

    public Set<String> getNumbers() {
        return numbers;
    }

    public void setNumbers(Set<String> numbers) {
        this.numbers = numbers;
    }

    public Set<String> getFeatures() {
        return features;
    }

    public void setFeatures(Set<String> features) {
        this.features = features;
    }

    public Set<String> getSeasons() {
        return seasons;
    }

    public void setSeasons(Set<String> seasons) {
        this.seasons = seasons;
    }

    public Set<RoomTypeRequest> getRoomTypes() {
        return roomTypes;
    }

    public void setRoomTypes(Set<RoomTypeRequest> roomTypes) {
        this.roomTypes = roomTypes;
    }
}
