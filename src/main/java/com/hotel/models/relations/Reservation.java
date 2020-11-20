package com.hotel.models.relations;

import com.hotel.models.Employee;
import com.hotel.models.Guest;
import com.hotel.models.Hotel;
import com.hotel.models.RoomType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Date start;

    @NotNull
    private Date end;

    private Boolean isAccepted;

    private String reason;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "guest_id")
    private Guest guest;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "receptionist_id")
    private Employee receptionist;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "room_type_id")
    private RoomType roomType;

    public Reservation() {
    }

    public Reservation(@NotNull Date start, @NotNull Date end) {
        this.start = start;
        this.end = end;
    }

    public Reservation(@NotNull Date start, @NotNull Date end, Hotel hotel, Guest guest, RoomType roomType) {
        this.start = start;
        this.end = end;
        this.hotel = hotel;
        this.guest = guest;
        this.roomType = roomType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public Employee getReceptionist() {
        return receptionist;
    }

    public void setReceptionist(Employee receptionist) {
        this.receptionist = receptionist;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }
}
