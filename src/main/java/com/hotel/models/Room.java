package com.hotel.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Integer number;

    @NotNull
    private Integer floor;

    private Boolean isEmpty;

    private Boolean isClear;

    @ManyToOne(targetEntity = RoomType.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "room_type_name")
    private RoomType type;

    public Room() {
    }

    public Room(@NotNull Integer number, @NotNull Integer floor) {
        this.number = number;
        this.floor = floor;
    }

    public Room(@NotNull Integer number, @NotNull Integer floor, @NotNull Boolean isEmpty, @NotNull Boolean isClear) {
        this.number = number;
        this.floor = floor;
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

    public RoomType getType() {
        return type;
    }

    public void setType(RoomType type) {
        this.type = type;
    }
}
