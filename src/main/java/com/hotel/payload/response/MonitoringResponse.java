package com.hotel.payload.response;

import java.sql.Timestamp;

public class MonitoringResponse {
	private Long id;

	private Boolean isAvailable;

	private Timestamp date;

	private Long receptionistId;

	private Long roomId;

	public MonitoringResponse(Long id, Boolean isAvailable, Timestamp date, Long receptionistId, Long roomId) {
		this.id = id;
		this.isAvailable = isAvailable;
		this.date = date;
		this.receptionistId = receptionistId;
		this.roomId = roomId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getAvailable() {
		return isAvailable;
	}

	public void setAvailable(Boolean available) {
		isAvailable = available;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public Long getReceptionistId() {
		return receptionistId;
	}

	public void setReceptionistId(Long receptionistId) {
		this.receptionistId = receptionistId;
	}

	public Long getRoomId() {
		return roomId;
	}

	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}
}
