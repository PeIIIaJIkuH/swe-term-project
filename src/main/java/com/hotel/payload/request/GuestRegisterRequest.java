package com.hotel.payload.request;

import java.util.Set;

public class GuestRegisterRequest extends RegisterRequest {
    private Set<String> types;

    public Set<String> getTypes() {
        return types;
    }

    public void setTypes(Set<String> types) {
        this.types = types;
    }
}
