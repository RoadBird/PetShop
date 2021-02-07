package com.newgen.serg.pet_shop.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter
@Setter
@Entity
@DiscriminatorValue("PET_LITTER")
public class PetLitter extends Product {
    @Column(name = "pet_type")
    private String petType;

    @Column
    private String weight;

    @Column
    private String constituents;
}
