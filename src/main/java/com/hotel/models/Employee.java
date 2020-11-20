package com.hotel.models;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "employee")
@PrimaryKeyJoinColumn(name = "id")
public class Employee extends User {
	@ManyToMany
	@JoinTable(name = "employee_role",
			joinColumns = @JoinColumn(name = "employee_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();

	@ManyToOne(optional = false)
	@JoinColumn(name = "hotel_id")
	private Hotel hotel;

	public Employee() {
	}

	public Employee(@NotNull @Size(max = 20) String username, @NotNull @Size(max = 50) @Email String email,
	                @NotNull @Size(max = 120) String password, @NotNull String firstName, @NotNull String lastName,
	                String homePhoneNumber, String mobilePhoneNumber, @NotNull String country, @NotNull String city,
	                @NotNull String street, @NotNull String idType, @NotNull Long idNumber) {
		super(username, email, password, firstName, lastName, homePhoneNumber, mobilePhoneNumber, country, city,
				street, idType, idNumber);
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}
}
