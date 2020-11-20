package com.hotel.models;

import javax.persistence.Embeddable;
import javax.validation.constraints.Size;

@Embeddable
public class GuestInfo {
	@Size(max = 50)
	private String firstName;

	@Size(max = 50)
	private String lastName;

	private String homePhoneNumber;

	private String mobilePhoneNumber;

	@Size(max = 50)
	private String country;

	@Size(max = 50)
	private String city;

	@Size(max = 50)
	private String street;

	private String idType;

	private Long idNumber;

	public GuestInfo() {
	}

	public GuestInfo(@Size(max = 50) String firstName, @Size(max = 50) String lastName, String homePhoneNumber,
	                 String mobilePhoneNumber, @Size(max = 50) String country, @Size(max = 50) String city,
	                 @Size(max = 50) String street, String idType, Long idNumber) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.homePhoneNumber = homePhoneNumber;
		this.mobilePhoneNumber = mobilePhoneNumber;
		this.country = country;
		this.city = city;
		this.street = street;
		this.idType = idType;
		this.idNumber = idNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getHomePhoneNumber() {
		return homePhoneNumber;
	}

	public void setHomePhoneNumber(String homePhoneNumber) {
		this.homePhoneNumber = homePhoneNumber;
	}

	public String getMobilePhoneNumber() {
		return mobilePhoneNumber;
	}

	public void setMobilePhoneNumber(String mobilePhoneNumber) {
		this.mobilePhoneNumber = mobilePhoneNumber;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getIdType() {
		return idType;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}

	public Long getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(Long idNumber) {
		this.idNumber = idNumber;
	}
}
