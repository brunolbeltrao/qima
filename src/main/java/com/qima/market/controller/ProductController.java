package com.qima.market.controller;

import com.qima.market.controller.dto.ProductDTO;
import com.qima.market.hexagono.domain.Product;
import com.qima.market.hexagono.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/products")
public class ProductController {
    private static Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO>  findProduct(@PathVariable Long id){
        Product currentProduct = productService.findProductById(id);

        if (currentProduct==null) {
            System.out.println("Product with id " + id + " not found");
            return new ResponseEntity<ProductDTO>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(currentProduct, HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity <ProductDTO> updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        Product currentProduct = productService.findProductById(id);

        if (currentProduct==null) {
            System.out.println("Product with id " + id + " not found");
            return new ResponseEntity<ProductDTO>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(productService.updateProduct(id, productDTO), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        Product currentProduct = productService.findProductById(id);

        if (currentProduct==null) {
            System.out.println("Product with id " + id + " not found");
            return new ResponseEntity<ProductDTO>(HttpStatus.NOT_FOUND);
        }

        productService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity <ProductDTO> product(@RequestBody ProductDTO productDTO){
        return new ResponseEntity<>(productService.product(productDTO), HttpStatus.CREATED);
    }
    @GetMapping("/sortedByPrice")
    public ResponseEntity<ProductDTO> getProductsSortedByPrice() {
        return new ResponseEntity(productService.getProductsSortedByPriceAsc(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ProductDTO> getAllProducts() {
        return new ResponseEntity(productService.allProducts(), HttpStatus.OK);
    }





}
