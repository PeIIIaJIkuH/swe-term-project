package com.hotel.payload.response;

import java.util.List;

public class GuestJwtResponse extends JwtResponse {
    private List<String> types;

    public GuestJwtResponse(String token, Long id, String username, String email, String firstName,
                            String lastName, String homePhoneNumber, String mobilePhoneNumber, String country,
                            String city, String street, String idType, Long idNumber, List<String> types) {
        super(token, id, username, email, firstName, lastName, homePhoneNumber, mobilePhoneNumber, country, city,street, idType, idNumber);
        this.types = types;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }
}
