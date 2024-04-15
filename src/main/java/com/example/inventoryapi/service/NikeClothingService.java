package com.example.inventoryapi.service;

import com.example.inventoryapi.domain.Clothing;
import com.example.inventoryapi.repo.ClothingRepository;
import reactor.core.publisher.Flux;

public class NikeClothingService extends DefaultClothingService {

    public NikeClothingService(ClothingRepository repository) {
        super(repository);
    }

    @Override
    public Flux<Clothing> findAll() {
        return findByBrand("Nike");
    }

}
