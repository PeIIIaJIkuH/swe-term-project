package com.hotel.payload.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class HotelPhoneNumberRequest {
    @NotBlank
    private String number;

    public HotelPhoneNumberRequest() {
    }

    public HotelPhoneNumberRequest(@NotBlank @Size(max = 12) String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
