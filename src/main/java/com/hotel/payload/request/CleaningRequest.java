package com.hotel.payload.request;

import javax.validation.constraints.NotNull;

public class CleaningRequest {
	@NotNull
	private Long cleaningWorkerId;

	@NotNull
	private Long roomId;

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
