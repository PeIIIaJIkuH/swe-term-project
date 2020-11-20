package com.hotel.payload.request;

import javax.validation.constraints.NotNull;
import java.sql.Date;

public class UpdateReservationRequest {
	@NotNull
	private Date start;

	@NotNull
	private Date end;

	@NotNull
	private Long roomTypeId;

	@NotNull
	private Long guestId;

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

	public Long getRoomTypeId() {
		return roomTypeId;
	}

	public void setRoomTypeId(Long roomTypeId) {
		this.roomTypeId = roomTypeId;
	}

	public Long getGuestId() {
		return guestId;
	}

	public void setGuestId(Long guestId) {
		this.guestId = guestId;
	}
}
