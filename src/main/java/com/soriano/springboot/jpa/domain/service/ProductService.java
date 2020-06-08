package com.soriano.springboot.jpa.domain.service;

import com.soriano.springboot.jpa.domain.model.Product;
import com.soriano.springboot.jpa.infrastructure.dao.entity.ProductEntity;

import java.util.List;

public interface ProductService {

    void save(Product product);
    List<Product> findAllProducts();
    Product findByIdProduct(Long id) throws Exception;

    void deleteProductById(Long id);
}
