package com.hotel.payload.response;

import java.sql.Timestamp;

public class CleaningResponse {
	private Long id;

	private Timestamp date;

	private Long cleaningWorkerId;

	private Long roomId;

	public CleaningResponse(Long id, Timestamp date, Long cleaningWorkerId, Long roomId) {
		this.id = id;
		this.date = date;
		this.cleaningWorkerId = cleaningWorkerId;
		this.roomId = roomId;
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

	public Long getCleaningWorkerId() {
		return cleaningWorkerId;
	}

	public void setCleaningWorkerId(Long cleaningWorkerId) {
		this.cleaningWorkerId = cleaningWorkerId;
	}

	public Long getRoomId() {
		return roomId;
	}

	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}
}
