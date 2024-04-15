package com.example.inventoryapi.repo;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.UUID;

@Repository
public interface ClothingRepository extends R2dbcRepository<ClothingEntity, UUID> {

    @Query("select * from clothing where brand = $0;")
    Flux<ClothingEntity> findByBrand(String brand);

}
