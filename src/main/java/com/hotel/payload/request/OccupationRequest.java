package com.hotel.payload.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hotel.models.GuestInfo;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

public class OccupationRequest {
	@NotNull
	private Long hotelId;

	@NotNull
	private Long roomId;

	@NotNull
	private Long receptionistId;

	@JsonFormat(pattern = "dd.MM.yyyy[ hh:MM:ss]")
	private Timestamp end;

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

	public Timestamp getEnd() {
		return end;
	}

	public void setEnd(Timestamp end) {
		this.end = end;
	}
}
