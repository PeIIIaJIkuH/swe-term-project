package com.hotel.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "type")
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(length = 50)
    private EType name;

    @NotNull
    private Integer discount;

    public Type() {
    }

    public Type(@NotNull EType name, @NotNull Integer discount) {
        this.name = name;
        this.discount = discount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EType getName() {
        return name;
    }

    public void setName(EType type) {
        this.name = type;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }
}
