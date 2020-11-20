//package com.hotel.models.relations;
//
//import com.hotel.models.Employee;
//import com.hotel.models.Room;
//
//import javax.persistence.*;
//import javax.validation.constraints.NotNull;
//import java.sql.Timestamp;
//
//@Entity
//@Table(name = "monitoring")
//public class Monitoring {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @NotNull
//    private Boolean isAvailable;
//
//    @NotNull
//    private Timestamp date;
//
//    @ManyToOne(optional = false)
//    private Employee employee;
//
//    @ManyToOne(optional = false)
//    private Room room;
//
//    public Monitoring() {
//    }
//
//    public Monitoring(Long id, @NotNull Boolean isAvailable, @NotNull Timestamp date) {
//        this.id = id;
//        this.isAvailable = isAvailable;
//        this.date = date;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public Boolean getAvailable() {
//        return isAvailable;
//    }
//
//    public void setAvailable(Boolean available) {
//        isAvailable = available;
//    }
//
//    public Timestamp getDate() {
//        return date;
//    }
//
//    public void setDate(Timestamp date) {
//        this.date = date;
//    }
//
//    public Employee getEmployee() {
//        return employee;
//    }
//
//    public void setEmployee(Employee employee) {
//        this.employee = employee;
//    }
//
//    public Room getRoom() {
//        return room;
//    }
//
//    public void setRoom(Room room) {
//        this.room = room;
//    }
//}
