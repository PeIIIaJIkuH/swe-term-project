package com.hotel.payload.request;

import com.hotel.models.Hotel;

import java.util.Set;

public class EmployeeRegisterRequest extends RegisterRequest {
    private Long hotelId;

    private Set<String> roles;

    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}
