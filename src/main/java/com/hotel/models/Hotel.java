package com.hotel.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "hotel")
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 50)
    private String name;

    @NotBlank
    @Size(max = 50)
    private String country;

    @NotBlank
    @Size(max = 50)
    private String city;

    @NotBlank
    @Size(max = 50)
    private String street;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "hotel_feature", joinColumns = @JoinColumn(name = "hotel_id"))
    @Column(name = "name")
    private Set<String> features;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "hotel_phone_number", joinColumns = @JoinColumn(name = "hotel_id"))
    @Column(name = "number")
    private Set<String> phoneNumbers;

    @ManyToMany
    @JoinTable(name = "hotel_season",
            joinColumns = @JoinColumn(name = "hotel_id"),
            inverseJoinColumns = @JoinColumn(name = "season_id"))
    private Set<Season> seasons = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL)
    private List<Employee> employees = new ArrayList<>();

    public Hotel() {
    }

    public Hotel(@NotBlank @Size(max = 50) String name, @NotBlank @Size(max = 50) String country,
                 @NotBlank @Size(max = 50) String city, @NotBlank @Size(max = 50) String street, Set<String> features,
                 Set<String> phoneNumbers) {
        this.name = name;
        this.country = country;
        this.city = city;
        this.street = street;
        this.features = features;
        this.phoneNumbers = phoneNumbers;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Set<String> getFeatures() {
        return features;
    }

    public void setFeatures(Set<String> features) {
        this.features = features;
    }

    public Set<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(Set<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public Set<Season> getSeasons() {
        return seasons;
    }

    public void setSeasons(Set<Season> seasons) {
        this.seasons = seasons;
    }
}
