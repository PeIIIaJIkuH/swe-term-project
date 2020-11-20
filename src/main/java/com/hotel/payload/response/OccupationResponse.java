package com.hotel.payload.response;

import com.hotel.models.GuestInfo;

import java.sql.Timestamp;

public class OccupationResponse {
	private Long id;
	private Timestamp start;
	private Timestamp end;
	private Float payment;
	private Long hotelId;
	private Long roomTypeId;
	private Long receptionistId;
	private Long guestId;
	private GuestInfo guestInfo;

	public OccupationResponse(Long id, Timestamp start, Timestamp end, Float payment, Long hotelId, Long roomTypeId,
	                          Long receptionistId) {
		this.id = id;
		this.start = start;
		this.end = end;
		this.payment = payment;
		this.hotelId = hotelId;
		this.roomTypeId = roomTypeId;
		this.receptionistId = receptionistId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Timestamp getStart() {
		return start;
	}

	public void setStart(Timestamp start) {
		this.start = start;
	}

	public Timestamp getEnd() {
		return end;
	}

	public void setEnd(Timestamp end) {
		this.end = end;
	}

	public Float getPayment() {
		return payment;
	}

	public void setPayment(Float payment) {
		this.payment = payment;
	}

	public Long getHotelId() {
		return hotelId;
	}

	public void setHotelId(Long hotelId) {
		this.hotelId = hotelId;
	}

	public Long getRoomTypeId() {
		return roomTypeId;
	}

	public void setRoomTypeId(Long roomTypeId) {
		this.roomTypeId = roomTypeId;
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

	public Long getGuestId() {
		return guestId;
	}

	public void setGuestId(Long guestId) {
		this.guestId = guestId;
	}
}
