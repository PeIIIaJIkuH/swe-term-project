package com.hotel.payload.request;

import javax.validation.constraints.NotNull;

public class RoomRequest {
    @NotNull
    private Integer number;
    @NotNull
    private Integer floor;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }
}
