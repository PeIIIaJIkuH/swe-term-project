package com.hotel.payload.request;

import javax.validation.constraints.NotBlank;

public class HotelFeatureRequest {
    @NotBlank
    private String name;

    public HotelFeatureRequest() {
    }

    public HotelFeatureRequest(@NotBlank String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
