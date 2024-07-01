package com.qima.market.repository;

import com.qima.market.hexagono.domain.Product;

import java.util.Date;

public interface ProductRepository {

    void delete(Product product);
    Product findById(Long id);
}
