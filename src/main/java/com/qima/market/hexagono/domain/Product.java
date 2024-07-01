package com.qima.market.hexagono.domain;

import com.qima.market.hexagono.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Product {

    @Autowired
    ProductService productService;

    private Long id;
    private Double price;
    private String description;
    private Category category;
    private boolean available;
    private String colour;



    public enum State {
        ACTIVE, CANCELED
    }
}
