package com.hotel.payload.response;

import com.hotel.models.RoomType;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HotelDetailedResponse {
	private Long id;

	private String name;

	private String country;

	private String city;

	private String street;

	private Set<String> features = new HashSet<>();

	private Set<String> numbers = new HashSet<>();

	private Set<RoomType> roomTypes = new HashSet<>();

	public HotelDetailedResponse(Long id, String name, String country, String city, String street,
	                             Set<String> features, Set<String> numbers, Set<RoomType> roomTypes) {
		this.id = id;
		this.name = name;
		this.country = country;
		this.city = city;
		this.street = street;
		this.features = features;
		this.numbers = numbers;
		this.roomTypes = roomTypes;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Set<String> getFeatures() {
		return features;
	}

	public void setFeatures(Set<String> features) {
		this.features = features;
	}

	public Set<String> getNumbers() {
		return numbers;
	}

	public void setNumbers(Set<String> numbers) {
		this.numbers = numbers;
	}

	public Set<RoomType> getRoomTypes() {
		return roomTypes;
	}

	public void setRoomTypes(Set<RoomType> roomTypes) {
		this.roomTypes = roomTypes;
	}
}
