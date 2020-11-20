package com.hotel.payload.response;

import java.sql.Date;

public class ReservationResponse {
	private Long id;
	private Date start;
	private Date end;
	private Boolean isAccepted;
	private String reason;
	private Long hotelId;
	private Long guestId;
	private Long roomTypeId;
	private Long receptionistId;

	public ReservationResponse(Long id, Date start, Date end, Boolean isAccepted, String reason, Long hotelId,
	                           Long guestId, Long roomTypeId) {
		this.id = id;
		this.start = start;
		this.end = end;
		this.isAccepted = isAccepted;
		this.reason = reason;
		this.hotelId = hotelId;
		this.guestId = guestId;
		this.roomTypeId = roomTypeId;
		this.receptionistId = receptionistId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public Boolean getAccepted() {
		return isAccepted;
	}

	public void setAccepted(Boolean accepted) {
		isAccepted = accepted;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Long getHotelId() {
		return hotelId;
	}

	public void setHotelId(Long hotelId) {
		this.hotelId = hotelId;
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

	public Long getReceptionistId() {
		return receptionistId;
	}

	public void setReceptionistId(Long receptionistId) {
		this.receptionistId = receptionistId;
	}
}
