package com.example.inventoryapi.config;

import com.example.inventoryapi.repo.ClothingRepository;
import com.example.inventoryapi.service.ClothingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    private final ClothingRepository repository;

    @Autowired
    public ServiceConfig(ClothingRepository repository) {
        this.repository = repository;
    }

    @Bean
    public ClothingService clothingService() {
        return new ClothingService(repository);
    }

}
