package com.hotel.payload.response;

import java.util.List;

public class EmployeeJwtResponse extends JwtResponse {
	private List<String> roles;

	public EmployeeJwtResponse(String token, Long id, String username, String email, String firstName,
	                           String lastName, String homePhoneNumber, String mobilePhoneNumber, String country,
	                           String city, String street, String idType, String idNumber, List<String> roles) {
		super(token, id, username, email, firstName, lastName, homePhoneNumber, mobilePhoneNumber, country, city,
                street, idType, idNumber);
		this.roles = roles;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
}
