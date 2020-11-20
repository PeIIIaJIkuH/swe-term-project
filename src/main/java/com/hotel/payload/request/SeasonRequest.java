package com.hotel.payload.request;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotNull;
import java.sql.Date;

public class SeasonRequest {
	@NotNull
	private String name;

	@NotNull
	@JsonFormat(pattern = "dd.MM.yyyy")
	private Date start;

	@NotNull
	@JsonFormat(pattern = "dd.MM.yyyy")
	private Date end;

	@NotNull
	private Float priceFactor;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Float getPriceFactor() {
		return priceFactor;
	}

	public void setPriceFactor(Float priceFactor) {
		this.priceFactor = priceFactor;
	}
}
