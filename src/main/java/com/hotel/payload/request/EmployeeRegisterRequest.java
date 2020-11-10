package com.hotel.payload.request;

import com.hotel.models.Hotel;

import java.util.Set;

public class EmployeeRegisterRequest extends RegisterRequest {
    private String hotelName;

    private Set<String> roles;

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}
