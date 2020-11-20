package com.hotel.models.relations;

import com.hotel.models.Employee;
import com.hotel.models.Guest;
import com.hotel.models.Room;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
@Table(name = "cleaning")
public class Cleaning {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Timestamp date;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cleaning_worker_id")
    private Employee cleaningWorker;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "room_id")
    private Room room;

    public Cleaning() {
    }

    public Cleaning(@NotNull Timestamp date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Employee getCleaningWorker() {
        return cleaningWorker;
    }

    public void setCleaningWorker(Employee cleaningWorker) {
        this.cleaningWorker = cleaningWorker;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
