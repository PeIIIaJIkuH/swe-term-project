package com.hotel.payload.response;

import java.sql.Timestamp;

public class GuestMessageResponse {
	private Long id;

	private Timestamp date;

	private String text;

	private Long managerId;

	private Long guestId;

	public GuestMessageResponse(Long id, Timestamp date, String text, Long managerId, Long guestId) {
		this.id = id;
		this.date = date;
		this.text = text;
		this.managerId = managerId;
		this.guestId = guestId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Long getManagerId() {
		return managerId;
	}

	public void setManagerId(Long managerId) {
		this.managerId = managerId;
	}

	public Long getGuestId() {
		return guestId;
	}

	public void setGuestId(Long guestId) {
		this.guestId = guestId;
	}
}
