package com.example.inventoryapi.service;

import com.example.inventoryapi.domain.Clothing;
import com.example.inventoryapi.repo.ClothingEntity;
import com.example.inventoryapi.repo.ClothingRepository;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.UUID;

@Slf4j
public class ClothingService implements ItemService<Clothing> {

    private final ClothingRepository repository;

    public ClothingService(ClothingRepository repository) {
        this.repository = repository;
    }

    @Override
    public Flux<Clothing> findAll() {
        return repository.findAll()
                .map(Clothing::from);
    }

    @Override
    public Mono<Clothing> findById(UUID id) {
        return repository.findById(id)
                .map(Clothing::from);
    }

    @Override
    public Mono<Clothing> create(Clothing clothing) {
        return repository.save(ClothingEntity.from(clothing)
                        .toBuilder()
                        .created(Instant.now())
                        .build())
                .map(Clothing::from);
    }

}
