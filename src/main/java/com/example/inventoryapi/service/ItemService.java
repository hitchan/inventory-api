package com.example.inventoryapi.service;

import com.example.inventoryapi.domain.Clothing;
import com.example.inventoryapi.domain.Item;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface ItemService<T extends Item> {

    Flux<T> findAll();

    Mono<Clothing> findById(UUID id);

    Mono<Clothing> create(Clothing clothing);

}
