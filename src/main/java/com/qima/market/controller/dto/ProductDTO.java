package com.qima.market.controller.dto;

import com.qima.market.hexagono.domain.Product;
import com.qima.market.hexagono.domain.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private Long id;
    private Double price;
    private String description;
    private Category category;
    private boolean available;
    private String colour;

    public static ProductDTO build(Product product) {
        return ProductDTO.builder()
                .id(product.getId())
                .category(product.getCategory())
                .price(product.getPrice())
                .description(product.getDescription())
                .available(product.isAvailable())
                .colour(product.getColour())
                .build();
    }
}
