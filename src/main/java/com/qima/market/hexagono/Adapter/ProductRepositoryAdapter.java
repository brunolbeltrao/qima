package com.qima.market.hexagono.Adapter;


import com.qima.market.controller.dto.ProductDTO;
import com.qima.market.hexagono.domain.Product;
import com.qima.market.hexagono.domain.Category;

import com.qima.market.repository.*;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Repository
public class ProductRepositoryAdapter implements ProductRepository {

    private final ProductRepositoryJpa productRepositoryJpa;
    private final CategoryRepositoryJpa categoryRepositoryJpa;

    public ProductRepositoryAdapter(ProductRepositoryJpa productRepositoryJpa, CategoryRepositoryJpa categoryRepositoryJpa) {
        this.productRepositoryJpa = productRepositoryJpa;
        this.categoryRepositoryJpa = categoryRepositoryJpa;
    }

    public Product save(Product product) {
        return build(productRepositoryJpa.save(buildEntity(product)));
    }



    public Product reproduct(Product product) {
        Product currentProduct = findProductById(product.getId());

        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(product.getId());
        productEntity.setCategory(buildCategoryEntity(currentProduct.getCategory()));

        return build(productRepositoryJpa.save(productEntity));
    }

    public void delete(Product product) {
        productRepositoryJpa.deleteById(product.getId());;
    }

    private Product build(ProductEntity productEntity) {
        return  Product.builder()
                .id(productEntity.getId())
                .description(productEntity.getDescription())
                .price(productEntity.getPrice())
                .available(productEntity.isAvailable())
                .colour(productEntity.getColour())
                .category(build(productEntity.getCategory()))
                .build();
    }

    private ProductEntity buildEntity(Product product) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(product.getId());
        productEntity.setCategory(buildCategoryEntity(product.getCategory()));
        productEntity.setPrice(product.getPrice());
        productEntity.setDescription(product.getDescription());
        productEntity.setAvailable(product.isAvailable());
        productEntity.setColour(product.getColour());
        return productEntity;
    }

    private Category build(CategoryEntity categoryEntity) {
        if (categoryEntity ==null){
            return null;
        }
        return Category.builder().id(categoryEntity.getId())
                .name(categoryEntity.getName()).build();
    }

    private CategoryEntity buildCategoryEntity(Category category){
        if (category == null){
            return  null;
        }
        return new CategoryEntity(category.getId(),category.getName());
    }

    @Override
    public Product findById(Long id) {
        ProductEntity productEntity = productRepositoryJpa.findById(id).get();
        Product product = build(productEntity);
        return product;
    }

    public Product findProductById(Long id) {
        Optional<ProductEntity> productOptional = productRepositoryJpa.findById(id);

        if (productOptional.isEmpty()){
            return  null;
        }
        Product product = build(productOptional.get());
        return product;
    }

    public ProductDTO product(Category category, Product product) {
        ProductEntity productEntity = new ProductEntity(buildCategoryEntity(product.getCategory()),
                product.getDescription(),  product.isAvailable() , product.getColour(),product.getPrice());

        productRepositoryJpa.save(productEntity);

        return(ProductDTO.builder().id(productEntity.getId()).price(productEntity.getPrice()).description(productEntity.getDescription())
                .available(productEntity.isAvailable()).colour(productEntity.getColour()).category(Category.builder()
                        .name(productEntity.getCategory().getName()).build()).build());

    }

    public List<Product> findProducts() {
        List<Product> products = new ArrayList<>();
        productRepositoryJpa.findAll().forEach(productEntity -> {
            products.add(build(productEntity));
        });
        return products;
    }

    public List<Product> findProductsSorted() {
        List<Product> products = new ArrayList<>();
        productRepositoryJpa.findAll(Sort.by(Sort.Direction.ASC, "price")).forEach(productEntity -> {
            products.add(build(productEntity));
        });
        return products;
    }
}

