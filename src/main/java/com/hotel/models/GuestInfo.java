package com.hotel.models;

import javax.persistence.Embeddable;

@Embeddable
public class GuestInfo {
	private String firstName;

	private String lastName;

	private String homePhoneNumber;

	private String mobilePhoneNumber;

	private String country;

	private String city;

	private String street;

	private String idType;

	private Long idNumber;

	public GuestInfo() {
	}

	public GuestInfo(String firstName, String lastName, String homePhoneNumber, String mobilePhoneNumber,
	                 String country, String city, String street, String idType, Long idNumber) {
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
