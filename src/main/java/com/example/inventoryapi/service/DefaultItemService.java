package com.example.inventoryapi.service;

import com.example.inventoryapi.domain.Item;
import com.example.inventoryapi.repo.ItemEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;
import java.util.function.Function;

public class DefaultItemService<E extends ItemEntity, T extends Item> implements ItemService<T> {

    private final ReactiveCrudRepository<E, UUID> repository;

    private final Function<E, T> converter;

    public DefaultItemService(ReactiveCrudRepository<E, UUID> repository, Function<E, T> converter) {
        this.repository = repository;
        this.converter = converter;
    }

    @Override
    public Flux<T> findAll() {
        return repository.findAll()
                .map(converter);
    }

    @Override
    public Mono<T> findById(UUID id) {
        return null;
    }

    @Override
    public Mono<T> create(T item) {
        return null;
    }

    @Override
    public Mono<T> update(T item) {
        return null;
    }
}
