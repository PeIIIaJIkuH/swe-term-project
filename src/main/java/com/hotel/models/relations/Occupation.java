package com.hotel.models.relations;

import com.hotel.models.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
@Table(name = "occupation")
public class Occupation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Timestamp start;

    private Timestamp end;

    private Float payment = 0F;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "room_id")
    private Room room;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "receptionist_id")
	private Employee receptionist;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "guest_id")
	private Guest guest;

	@Embedded
	private GuestInfo guestInfo;

    public Occupation() {
    }

	public Occupation(@NotNull Timestamp start) {
		this.start = start;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Timestamp getStart() {
		return start;
	}

	public void setStart(Timestamp start) {
		this.start = start;
	}

	public Timestamp getEnd() {
		return end;
	}

	public void setEnd(Timestamp end) {
		this.end = end;
	}

	public Float getPayment() {
		return payment;
	}

	public void setPayment(Float payment) {
		this.payment = payment;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public Employee getReceptionist() {
		return receptionist;
	}

	public void setReceptionist(Employee receptionist) {
		this.receptionist = receptionist;
	}

	public GuestInfo getGuestInfo() {
		return guestInfo;
	}

	public void setGuestInfo(GuestInfo guestInfo) {
		this.guestInfo = guestInfo;
	}

	public Guest getGuest() {
		return guest;
	}

	public void setGuest(Guest guest) {
		this.guest = guest;
	}
}
