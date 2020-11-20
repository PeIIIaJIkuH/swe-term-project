package com.hotel.models;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "guest")
@PrimaryKeyJoinColumn(name = "id")
public class Guest extends User {
	@ManyToMany
	@JoinTable(name = "guest_type",
			joinColumns = @JoinColumn(name = "guest_id"),
			inverseJoinColumns = @JoinColumn(name = "type_id"))
	private Set<Type> types = new HashSet<>();

	public Guest() {
	}

	public Guest(@NotNull @Size(max = 20) String username, @NotNull @Size(max = 50) @Email String email,
	             @NotNull @Size(max = 120) String password, @NotNull String firstName, @NotNull String lastName,
	             String homePhoneNumber, String mobilePhoneNumber, @NotNull String country, @NotNull String city,
	             @NotNull String street, @NotNull String idType, @NotNull Long idNumber) {
		super(username, email, password, firstName, lastName, homePhoneNumber, mobilePhoneNumber, country, city,
				street, idType, idNumber);
	}

	public Set<Type> getTypes() {
		return types;
	}

	public void setTypes(Set<Type> types) {
		this.types = types;
	}
}
