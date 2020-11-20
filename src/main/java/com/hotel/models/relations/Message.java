//package com.hotel.models.relations;
//
//import com.hotel.models.Employee;
//import com.hotel.models.Guest;
//
//import javax.persistence.*;
//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.NotNull;
//import java.sql.Timestamp;
//
//@Entity
//@Table(name = "message")
//public class Message {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @NotNull
//    private Timestamp date;
//
//    @NotBlank
//    private String text;
//
//    @ManyToOne(optional = false)
//    private Employee employee;
//
//    @ManyToOne(optional = false)
//    private Guest guest;
//
//    public Message() {
//    }
//
//    public Message(@NotNull Timestamp date, @NotBlank String text) {
//        this.date = date;
//        this.text = text;
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
//    public Timestamp getDate() {
//        return date;
//    }
//
//    public void setDate(Timestamp date) {
//        this.date = date;
//    }
//
//    public String getText() {
//        return text;
//    }
//
//    public void setText(String text) {
//        this.text = text;
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
//    public Guest getGuest() {
//        return guest;
//    }
//
//    public void setGuest(Guest guest) {
//        this.guest = guest;
//    }
//}
