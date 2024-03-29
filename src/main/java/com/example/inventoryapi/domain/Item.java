package com.example.inventoryapi.domain;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Data
@SuperBuilder
public class Item implements Trackable {

    private final UUID id;

    private final Status status;

    private final Condition condition;

    public enum Status {
        PRESENT,
        ISSUED
    }

    public enum Condition {
        NEW,
        LIKE_NEW,
        FAIR,
        POOR
    }

}
