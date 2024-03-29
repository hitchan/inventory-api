package com.example.inventoryapi.route;

import com.example.inventoryapi.domain.Clothing;
import com.example.inventoryapi.domain.Item;
import com.example.inventoryapi.service.ClothingService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CreateClothingHandler implements HandlerFunction<ServerResponse> {

    private final ClothingService service;

    public CreateClothingHandler(ClothingService service) {
        this.service = service;
    }

    @Override
    public Mono<ServerResponse> handle(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(Request.class)
                .flatMap(request -> service.create(Clothing.builder()
                                .brand(request.getBrand())
                                .color(request.getColor())
                                .condition(request.getCondition())
                                .status(request.getStatus())
                        .build()))
                .flatMap(clothing -> ServerResponse.ok()
                        .body(BodyInserters.fromValue(Response.from(clothing))));
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static final class Request {

        private Item.Status status;

        private String brand;

        private String color;

        private Item.Condition condition;

    }

    @Data
    @Builder
    public static final class Response {

        private final UUID id;

        private final Item.Status status;

        private final Item.Condition condition;

        private final String brand;

        private final String color;

        public static Response from(Clothing clothing) {
            return builder()
                    .id(clothing.getId())
                    .status(clothing.getStatus())
                    .condition(clothing.getCondition())
                    .brand(clothing.getBrand())
                    .color(clothing.getColor())
                    .build();
        }

    }

}
