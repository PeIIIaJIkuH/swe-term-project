package com.hotel.payload.request;

import javax.validation.constraints.NotNull;

public class MessageRequest {
	@NotNull
	private String text;

	@NotNull
	private Long managerId;

	@NotNull
	private Long guestId;

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
