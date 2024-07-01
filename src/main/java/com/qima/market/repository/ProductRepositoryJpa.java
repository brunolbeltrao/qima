package com.qima.market.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.Optional;


public interface ProductRepositoryJpa extends JpaRepository <ProductEntity, Long> {
}

