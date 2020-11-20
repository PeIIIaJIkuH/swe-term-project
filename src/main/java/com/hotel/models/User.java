package com.hotel.models;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "user",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        })
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 20)
    private String username;

    @NotNull
    @Size(max = 50)
    @Email
    private String email;

    @NotNull
    @Size(max = 120)
    private String password;

    @NotNull
    @Size(max = 50)
    private String firstName;

    @NotNull
    @Size(max = 50)
    private String lastName;

    private String homePhoneNumber;

    private String mobilePhoneNumber;

    @NotNull
    @Size(max = 50)
    private String country;

    @NotNull
    @Size(max = 50)
    private String city;

    @NotNull
    @Size(max = 50)
    private String street;

    @NotNull
    private String idType;

    @NotNull
    private Long idNumber;

    public User() {
    }

    public User(@NotNull @Size(max = 20) String username, @NotNull @Size(max = 50) @Email String email,
                @NotNull @Size(max = 120) String password, @NotNull @Size(max = 50) String firstName,
                @NotNull @Size(max = 50) String lastName, @Size(max = 12) String homePhoneNumber,
                @Size(max = 12) String mobilePhoneNumber, @NotNull @Size(max = 50) String country,
                @NotNull @Size(max = 50) String city, @NotNull @Size(max = 50) String street,
                @NotNull @Size(max = 25) String idType, @NotNull Long idNumber) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.homePhoneNumber = homePhoneNumber;
        this.mobilePhoneNumber = mobilePhoneNumber;
        this.country = country;
        this.city = city;
        this.street = street;
        this.idType = idType;
        this.idNumber = idNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHomePhoneNumber() {
        return homePhoneNumber;
    }

    public void setHomePhoneNumber(String homePhoneNumber) {
        this.homePhoneNumber = homePhoneNumber;
    }

    public String getMobilePhoneNumber() {
        return mobilePhoneNumber;
    }

    public void setMobilePhoneNumber(String mobilePhoneNumber) {
        this.mobilePhoneNumber = mobilePhoneNumber;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public Long getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(Long idNumber) {
        this.idNumber = idNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
