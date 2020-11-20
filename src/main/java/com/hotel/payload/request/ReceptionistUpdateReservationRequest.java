package com.hotel.payload.request;

import javax.validation.constraints.NotNull;

public class ReceptionistUpdateReservationRequest {
	@NotNull
	private Boolean isAccepted;

	private String reason;

	@NotNull
	private Long receptionistId;

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

	public Long getReceptionistId() {
		return receptionistId;
	}

	public void setReceptionistId(Long receptionistId) {
		this.receptionistId = receptionistId;
	}
}
