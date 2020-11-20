package com.hotel.payload.request;

import javax.validation.constraints.NotNull;

public class HotelPhoneNumberRequest {
    @NotNull
    private String number;

    public HotelPhoneNumberRequest() {
    }

    public HotelPhoneNumberRequest(@NotNull String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
