package com.hotel.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Entity
@Table(name = "season")
public class Season {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private String name;

	@NotNull
	private Date start;

	@NotNull
	private Date end;

	@NotNull
	private Float priceFactor;

	public Season() {
	}

	public Season(@NotNull String name, @NotNull Date start, @NotNull Date end, @NotNull Float priceFactor) {
		this.name = name;
		this.start = start;
		this.end = end;
		this.priceFactor = priceFactor;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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
