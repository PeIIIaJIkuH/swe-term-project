package com.hotel.payload.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class HotelFeatureRequest {
    @NotNull
    private String name;

    public HotelFeatureRequest() {
    }

    public HotelFeatureRequest(@NotNull String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
