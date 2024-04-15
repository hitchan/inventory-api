package com.example.inventoryapi.service;

import com.example.inventoryapi.domain.Clothing;
import reactor.core.publisher.Flux;

public interface ClothingService extends ItemService<Clothing> {

    Flux<Clothing> findByBrand(String brand);

}
