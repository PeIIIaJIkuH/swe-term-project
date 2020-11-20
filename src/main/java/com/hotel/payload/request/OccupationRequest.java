package com.hotel.payload.request;

import com.hotel.models.GuestInfo;

import javax.validation.constraints.NotNull;

public class OccupationRequest {
	@NotNull
	private Long hotelId;

	@NotNull
	private Long roomId;

	@NotNull
	private Long receptionistId;

	private GuestInfo guestInfo;

	private String guestUsername;

	public Long getHotelId() {
		return hotelId;
	}

	public void setHotelId(Long hotelId) {
		this.hotelId = hotelId;
	}

	public Long getRoomId() {
		return roomId;
	}

	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}

	public Long getReceptionistId() {
		return receptionistId;
	}

	public void setReceptionistId(Long receptionistId) {
		this.receptionistId = receptionistId;
	}

	public GuestInfo getGuestInfo() {
		return guestInfo;
	}

	public void setGuestInfo(GuestInfo guestInfo) {
		this.guestInfo = guestInfo;
	}

	public String getGuestUsername() {
		return guestUsername;
	}

	public void setGuestUsername(String guestUsername) {
		this.guestUsername = guestUsername;
	}
}
