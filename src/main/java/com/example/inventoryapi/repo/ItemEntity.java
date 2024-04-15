package com.example.inventoryapi.repo;

import com.example.inventoryapi.domain.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Getter
@Setter
public class ItemEntity {

    @Id
    private UUID id;

    @Column
    private Item.Status status;

    @Column
    private Item.Condition condition;

}
