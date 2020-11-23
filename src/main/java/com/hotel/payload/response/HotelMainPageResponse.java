package com.hotel.payload.response;

public class HotelMainPageResponse {
	private Long id;

	private String name;

	private String country;

	private String city;

	private Integer avgPrice;

	public HotelMainPageResponse(Long id, String name, String country, String city, Integer avgPrice) {
		this.id = id;
		this.name = name;
		this.country = country;
		this.city = city;
		this.avgPrice = avgPrice;
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

	public Integer getAvgPrice() {
		return avgPrice;
	}

	public void setAvgPrice(Integer avgPrice) {
		this.avgPrice = avgPrice;
	}
}
