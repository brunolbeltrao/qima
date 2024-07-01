package com.qima.market.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface CategoryRepositoryJpa extends JpaRepository <CategoryEntity, Long> {

    @Query("select s from CategoryEntity s where s.name LIKE %:name% ")
    CategoryEntity findCategoryByName(@Param("name") String name);
}

