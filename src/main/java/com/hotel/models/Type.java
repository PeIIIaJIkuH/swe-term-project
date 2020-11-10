package com.hotel.models;

import javax.persistence.*;

@Entity
@Table(name = "type")
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 50)
    private EType name;

    private Integer discount;

    public Type() {
    }

    public Type(EType name, Integer discount) {
        this.name = name;
        this.discount = discount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
