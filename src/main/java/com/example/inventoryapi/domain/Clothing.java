package com.example.inventoryapi.domain;

import com.example.inventoryapi.repo.ClothingEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class Clothing extends Item {

    private final String brand;

    private final String color;

    public static Clothing from(ClothingEntity entity) {
        return Clothing.builder()
                .id(entity.getId())
                .status(entity.getStatus())
                .brand(entity.getBrand())
                .color(entity.getColor())
                .condition(entity.getCondition())
                .build();
    }

}

