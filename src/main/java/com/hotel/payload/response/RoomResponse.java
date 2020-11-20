package com.hotel.payload.response;

public class RoomResponse {
	private Long id;
	private Integer number;
	private Integer floor;
	private Long typeId;
	private Boolean isEmpty;
	private Boolean isClear;

	public RoomResponse(Long id, Integer number, Integer floor, Long typeId, Boolean isEmpty, Boolean isClear) {
		this.id = id;
		this.number = number;
		this.floor = floor;
		this.typeId = typeId;
		this.isEmpty = isEmpty;
		this.isClear = isClear;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Integer getFloor() {
		return floor;
	}

	public void setFloor(Integer floor) {
		this.floor = floor;
	}

	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	public Boolean getEmpty() {
		return isEmpty;
	}

	public void setEmpty(Boolean empty) {
		isEmpty = empty;
	}

	public Boolean getClear() {
		return isClear;
	}

	public void setClear(Boolean clear) {
		isClear = clear;
	}
}
