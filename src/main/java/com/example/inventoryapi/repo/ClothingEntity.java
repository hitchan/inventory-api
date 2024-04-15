package com.example.inventoryapi.repo;

import com.example.inventoryapi.domain.Clothing;
import com.example.inventoryapi.domain.Item;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Getter
@Setter
@Table("clothing")
public class ClothingEntity extends ItemEntity {

    @Column
    private String brand;

    @Column
    private String color;

    @Column
    private Instant created;

    @Column
    private Instant updated;

    public static ClothingEntity from(Clothing clothing) {
        return builder()
                .id(clothing.getId())
                .brand(clothing.getBrand())
                .condition(clothing.getCondition())
                .color(clothing.getColor())
                .status(clothing.getStatus())
                .build();
    }

}
