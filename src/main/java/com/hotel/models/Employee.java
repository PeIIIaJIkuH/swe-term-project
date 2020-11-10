package com.hotel.models;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "employee")
@PrimaryKeyJoinColumn(name = "id")
public class Employee extends User {
    @ManyToMany
    @JoinTable(name = "employee_role",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @ManyToOne(optional = false)
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    public Employee() {
    }

    public Employee(@NotBlank @Size(max = 20) String username, @NotBlank @Size(max = 50) @Email String email,
                    @NotBlank @Size(max = 120) String password, @NotBlank @Size(max = 50) String firstName,
                    @NotBlank @Size(max = 50) String lastName, @Size(max = 12) String homePhoneNumber,
                    @Size(max = 12) String mobilePhoneNumber, @Size(max = 50) String country,
                    @Size(max = 50) String city, @Size(max = 50) String street, @Size(max = 25) String idType,
                    Long idNumber) {
        super(username, email, password, firstName, lastName, homePhoneNumber, mobilePhoneNumber, country, city, street, idType, idNumber);
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
}
