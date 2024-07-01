package com.qima.market.hexagono.service;

import com.qima.market.controller.dto.ProductDTO;
import com.qima.market.hexagono.Adapter.ProductRepositoryAdapter;
import com.qima.market.hexagono.Adapter.CategoryRepositoryAdapter;
import com.qima.market.hexagono.domain.Product;
import com.qima.market.hexagono.domain.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ProductService {
    @Autowired
    private ProductRepositoryAdapter productRepositoryAdapter;

    @Autowired
    private CategoryRepositoryAdapter categoryRepositoryAdapter;

    public ProductDTO updateProduct(Long idProduct, ProductDTO productDTO) {
        Product product = Product.builder()
                .id(idProduct)
                .price(productDTO.getPrice())
                .description(productDTO.getDescription())
                .available(productDTO.isAvailable())
                .colour(productDTO.getColour())
                .category(productDTO.getCategory())

                .build();
        return ProductDTO.build(productRepositoryAdapter.save(product));
    }

    public Product findProductById(Long id) {
        return productRepositoryAdapter.findProductById(id);
    }

    public void delete(Long id) {
        Product product = productRepositoryAdapter.findProductById(id);
        productRepositoryAdapter.delete(product);
    }
    private Category solveCategory(String categoryName){
        Category category = categoryRepositoryAdapter.findCategoryByName(categoryName);
        if(category != null){
            return category;
        }else{
            return categoryRepositoryAdapter.save(Category.builder().name(categoryName).build());
        }
    }

    public ProductDTO product(ProductDTO productDTO) {
        Category category = solveCategory(productDTO.getCategory().getName());

        Product product = Product.builder()
                    .category(category)
                    .price(productDTO.getPrice())
                    .description(productDTO.getDescription())
                    .available(productDTO.isAvailable())
                    .colour(productDTO.getColour())
                    .build();

        return productRepositoryAdapter.product(category, product);
    }


    public List<ProductDTO> allProducts() {
        List<ProductDTO> productDTOS = new ArrayList<>();
        List<Product> products = productRepositoryAdapter.findProducts();

        products.stream().forEach(product -> {
            productDTOS.add(ProductDTO.builder()
                    .id(product.getId())
                    .category(product.getCategory())
                    .price(product.getPrice())
                    .description(product.getDescription())
                    .available(product.isAvailable())
                    .colour(product.getColour())

                    .build());
        });
        return productDTOS;
    }

    public List<ProductDTO> getProductsSortedByPriceAsc() {
        List<ProductDTO> productDTOS = new ArrayList<>();
        List<Product> products = productRepositoryAdapter.findProductsSorted();

        products.stream().forEach(product -> {
            productDTOS.add(ProductDTO.builder()
                    .id(product.getId())
                    .category(product.getCategory())
                    .price(product.getPrice())
                    .description(product.getDescription())
                    .available(product.isAvailable())
                    .colour(product.getColour())

                    .build());
        });
        return productDTOS;
    }

}
