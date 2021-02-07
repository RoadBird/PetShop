package com.newgen.serg.pet_shop.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "product")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
public class Product {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Column(insertable = false, updatable = false)
    private String type;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private BigDecimal price;

    @Column
    private Long count;

    @Column(name = "img_url")
    private String imgUrl;

    @Column
    private LocalDate entrance;

    @Column
    private String importer;
}
