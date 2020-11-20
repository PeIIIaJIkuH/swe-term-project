package com.hotel.models.relations;

import com.hotel.models.Employee;
import com.hotel.models.Room;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
@Table(name = "monitoring")
public class Monitoring {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private Boolean isAvailable;

	@NotNull
	private Timestamp date;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "receptionist_id")
    private Employee receptionist;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "room_id")
    private Room room;

	public Monitoring() {
	}

    public Monitoring(@NotNull Boolean isAvailable, @NotNull Timestamp date) {
        this.isAvailable = isAvailable;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Employee getReceptionist() {
        return receptionist;
    }

    public void setReceptionist(Employee receptionist) {
        this.receptionist = receptionist;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
