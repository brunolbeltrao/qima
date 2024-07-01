package com.qima.market.repository;

import com.qima.market.hexagono.domain.Category;

public interface CategoryRepository {

    void delete(Category student);
    Category findById(Long id);
}
