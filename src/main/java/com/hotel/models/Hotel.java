package com.hotel.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "hotel",
        uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String country;

    @NotNull
    private String city;

    @NotNull
    private String street;

    @ElementCollection
    @CollectionTable(name = "hotel_feature",
            joinColumns = @JoinColumn(name = "hotel_id", referencedColumnName = "id", nullable = false))
    @Column(name = "name", nullable = false)
    private Set<String> features = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "hotel_phone",
            joinColumns = @JoinColumn(name = "hotel_id", referencedColumnName = "id", nullable = false))
    @Column(name = "number", nullable = false, unique = true)
    private Set<String> numbers = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "hotel_season",
            joinColumns = @JoinColumn(name = "hotel_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "season_id", referencedColumnName = "id"))
    private Set<Season> seasons = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name = "hotel_rooms",
            joinColumns = @JoinColumn(name = "hotel_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "room_id", referencedColumnName = "id"))
    private Set<Room> rooms = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name = "hotel_room_types",
            joinColumns = @JoinColumn(name = "hotel_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "room_type_id", referencedColumnName = "id"))
    private Set<RoomType> roomTypes = new HashSet<>();

    public Hotel() {
    }

    public Hotel(@NotNull String name, @NotNull String country, @NotNull String city, @NotNull String street) {
        this.name = name;
        this.country = country;
        this.city = city;
        this.street = street;
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

    public Set<String> getNumbers() {
        return numbers;
    }

    public void setNumbers(Set<String> numbers) {
        this.numbers = numbers;
    }

    public Set<Season> getSeasons() {
        return seasons;
    }

    public void setSeasons(Set<Season> seasons) {
        this.seasons = seasons;
    }

    public Set<Room> getRooms() {
        return rooms;
    }

    public void setRooms(Set<Room> rooms) {
        this.rooms = rooms;
    }

    public Set<RoomType> getRoomTypes() {
        return roomTypes;
    }

    public void setRoomTypes(Set<RoomType> roomTypes) {
        this.roomTypes = roomTypes;
    }
}
