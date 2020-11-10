package com.hotel.models;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "guest")
@PrimaryKeyJoinColumn(name = "id")
public class Guest extends User {
    @ManyToMany
    @JoinTable(name = "guest_type",
    joinColumns = @JoinColumn(name = "guest_id"),
    inverseJoinColumns = @JoinColumn(name = "type_id"))
    private Set<Type> types = new HashSet<>();

    public Guest() {
    }

    public Guest(@NotBlank @Size(max = 20) String username, @NotBlank @Size(max = 50) @Email String email,
                 @NotBlank @Size(max = 120) String password, @NotBlank @Size(max = 50) String firstName,
                 @NotBlank @Size(max = 50) String lastName, @Size(max = 12) String homePhoneNumber,
                 @Size(max = 12) String mobilePhoneNumber, @Size(max = 50) String country, @Size(max = 50) String city,
                 @Size(max = 50) String street, @Size(max = 25) String idType, Long idNumber) {
        super(username, email, password, firstName, lastName, homePhoneNumber, mobilePhoneNumber, country, city, street, idType, idNumber);
    }

    public Set<Type> getTypes() {
        return types;
    }

    public void setTypes(Set<Type> types) {
        this.types = types;
    }
}
