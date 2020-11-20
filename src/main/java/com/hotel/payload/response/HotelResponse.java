package com.hotel.payload.response;

import java.util.ArrayList;
import java.util.List;

public class HotelResponse {
	private Long id;

	private String name;

	private String country;

	private String city;

	private String street;

	private List<String> features = new ArrayList<>();

	private List<String> numbers = new ArrayList<>();

	private List<Long> seasonIds = new ArrayList<>();

	private List<Long> roomIds = new ArrayList<>();

	private List<Long> roomTypeIds = new ArrayList<>();


	public HotelResponse(Long id, String name, String country, String city, String street) {
		this.id = id;
		this.name = name;
		this.country = country;
		this.city = city;
		this.street = street;
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

	public List<String> getFeatures() {
		return features;
	}

	public void setFeatures(List<String> features) {
		this.features = features;
	}

	public List<String> getNumbers() {
		return numbers;
	}

	public void setNumbers(List<String> numbers) {
		this.numbers = numbers;
	}

	public List<Long> getSeasonIds() {
		return seasonIds;
	}

	public void setSeasonIds(List<Long> seasonIds) {
		this.seasonIds = seasonIds;
	}

	public List<Long> getRoomIds() {
		return roomIds;
	}

	public void setRoomIds(List<Long> roomIds) {
		this.roomIds = roomIds;
	}

	public List<Long> getRoomTypeIds() {
		return roomTypeIds;
	}

	public void setRoomTypeIds(List<Long> roomTypeIds) {
		this.roomTypeIds = roomTypeIds;
	}
}
