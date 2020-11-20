package com.hotel.payload.request;

import javax.validation.constraints.NotNull;

public class MonitoringRequest {
	@NotNull
	private Long receptionistId;

	@NotNull
	private Long roomId;

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
