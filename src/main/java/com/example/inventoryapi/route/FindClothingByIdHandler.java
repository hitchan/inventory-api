package com.example.inventoryapi.route;

import com.example.inventoryapi.config.RouteConfig;
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

import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

public class FindClothingByIdHandler implements HandlerFunction<ServerResponse> {

    private final ClothingService service;

    public FindClothingByIdHandler(ClothingService service) {
        this.service = service;
    }

    @Override
    public Mono<ServerResponse> handle(ServerRequest serverRequest) {
        final Request request = Request.from(serverRequest);
        return service.findById(request.getId())
                .flatMap(clothing -> ServerResponse.ok()
                        .body(BodyInserters.fromValue(Response.from(clothing))))
                .switchIfEmpty(ServerResponse.notFound()
                        .build());
    }

    @Data
    @Builder
    public static final class Request {

        private UUID id;

        public static Request from(ServerRequest request) {
            return builder()
                    .id(UUID.fromString(request.pathVariable(RouteConfig.ID_VARIABLE)))
                    .build();
        }

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
