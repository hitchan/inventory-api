package com.example.inventoryapi.route;

import com.example.inventoryapi.domain.Clothing;
import com.example.inventoryapi.domain.Item;
import com.example.inventoryapi.service.ClothingService;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class FetchAllClothingHandler implements HandlerFunction<ServerResponse> {

    private final ClothingService service;

    public FetchAllClothingHandler(ClothingService service) {
        this.service = service;
    }

    @Override
    public Mono<ServerResponse> handle(ServerRequest request) {
        return service.findAll()
                .collectList()
                .flatMap(clothing -> ServerResponse.ok()
                        .body(BodyInserters.fromValue(Response.from(clothing))));
    }

    @Data
    @Builder
    public static final class Response {

        private final List<Entry> entries;

        public static Response from(Collection<Clothing> clothing) {
            return builder()
                    .entries(clothing.stream()
                            .map(Entry::from)
                            .collect(Collectors.toList()))
                    .build();
        }

    }

    @Data
    @Builder
    public static final class Entry {

        private final UUID id;

        private final Item.Status status;

        private final Item.Condition condition;

        private final String brand;

        private final String color;

        public static Entry from(Clothing clothing) {
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
