package com.qima.market.repository;

import com.qima.market.hexagono.domain.Category;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name="product")
public class ProductEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "gest_id")
    private CategoryEntity category;
    private String description;
    private Double price;
    private boolean available;
    private String colour;


    public ProductEntity() {
    }

    public ProductEntity(CategoryEntity category, String description, boolean available, String colour, Double price) {
        this.category = category;
        this.description = description;
        this.available = available;
        this.colour = colour;
        this.price = price;
    }
}
