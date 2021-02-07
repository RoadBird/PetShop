package com.newgen.serg.pet_shop.entity;

import com.newgen.serg.pet_shop.entity.enums.UserRole;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@Table(name = "shop_user")
public class ShopUser {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @NotBlank
    @Column
    private String email;

    @NotNull
    @NotBlank
    @Column
    private String name;

    @NotNull
    @NotBlank
    @Column
    private String password;

    @NotNull
    @Column
    private UserRole role;
}
