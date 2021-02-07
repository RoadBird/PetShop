package com.newgen.serg.pet_shop.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter
@Setter
@Entity
@DiscriminatorValue("STUFF")
public class Stuff extends Product {
    @Column
    private String size;

    @Column
    private String material;

    @Column
    private String color;

    @Column(name = "max_weight")
    private String maxWeight;
}
