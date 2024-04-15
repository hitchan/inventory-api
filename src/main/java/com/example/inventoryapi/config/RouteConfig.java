package com.example.inventoryapi.config;

import com.example.inventoryapi.route.CreateClothingHandler;
import com.example.inventoryapi.route.FetchAllClothingHandler;
import com.example.inventoryapi.route.FindClothingByIdHandler;
import com.example.inventoryapi.service.ClothingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouteConfig {

    public static final String CLOTHING_PATH = "/clothing";

    public static final String ID_VARIABLE = "id";

    public static final String FIND_CLOTHING_BY_ID_PATH = CLOTHING_PATH + "/{" + ID_VARIABLE + "}";

    private final ClothingService clothingService;

    @Autowired
    public RouteConfig(ClothingService clothingService) {
        this.clothingService = clothingService;
    }

    @Bean
    public RouterFunction<ServerResponse> fetchAllClothingRoute() {
        return route(GET(CLOTHING_PATH), fetchAllClothingHandler());
    }

    @Bean
    public HandlerFunction<ServerResponse> fetchAllClothingHandler() {
        return new FetchAllClothingHandler(clothingService);
    }

    @Bean
    public RouterFunction<ServerResponse> createClothingRoute() {
        return route(POST(CLOTHING_PATH), createClothingHandler());
    }

    @Bean
    public HandlerFunction<ServerResponse> createClothingHandler() {
        return new CreateClothingHandler(clothingService);
    }

    @Bean
    public RouterFunction<ServerResponse> findClothingByIdRoute() {
        return route(GET(FIND_CLOTHING_BY_ID_PATH), findClothingByIdHandler());
    }

    @Bean
    public HandlerFunction<ServerResponse> findClothingByIdHandler() {
        return new FindClothingByIdHandler(clothingService);
    }

}
