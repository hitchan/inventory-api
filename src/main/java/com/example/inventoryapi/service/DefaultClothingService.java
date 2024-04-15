package com.example.inventoryapi.service;

import com.example.inventoryapi.domain.Clothing;
import com.example.inventoryapi.repo.ClothingEntity;
import com.example.inventoryapi.repo.ClothingRepository;
import reactor.core.publisher.Flux;

import java.util.function.Function;

public class DefaultClothingService extends DefaultItemService<ClothingEntity, Clothing> implements ClothingService {

    private final ClothingRepository repository;

    public DefaultClothingService(ClothingRepository repository) {
        super(repository, converter());
        this.repository = repository;
    }

    @Override
    public Flux<Clothing> findByBrand(String brand) {
        return repository.findByBrand(brand)
                .map(Clothing::from);
    }

    private static Function<ClothingEntity, Clothing> converter() {
        return Clothing::from;
    }

}
