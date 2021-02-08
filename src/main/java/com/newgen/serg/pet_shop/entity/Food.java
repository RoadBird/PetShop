package com.newgen.serg.pet_shop.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@DiscriminatorValue("FOOD")
public class Food extends Product {
    @Column
    private LocalDate expiration;

    @Column(name = "pet_type")
    private String petType;

    @Column
    private String weight;

    @Column(columnDefinition = "text")
    private String constituents;
}
