package com.hotel.payload.request;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotNull;
import java.sql.Date;

public class ReservationRequest {
	@NotNull
	@JsonFormat(pattern = "dd.MM.yyyy")
	private Date start;

	@NotNull
	@JsonFormat(pattern = "dd.MM.yyyy")
	private Date end;

	@NotNull
	private Long hotelId;

	@NotNull
	private Long guestId;

	@NotNull
	private Long roomTypeId;

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public Long getGuestId() {
		return guestId;
	}

	public void setGuestId(Long guestId) {
		this.guestId = guestId;
	}

	public Long getRoomTypeId() {
		return roomTypeId;
	}

	public void setRoomTypeId(Long roomTypeId) {
		this.roomTypeId = roomTypeId;
	}

	public Long getHotelId() {
		return hotelId;
	}

	public void setHotelId(Long hotelId) {
		this.hotelId = hotelId;
	}
}
