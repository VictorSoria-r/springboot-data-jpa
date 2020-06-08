package com.soriano.springboot.jpa.infrastructure.service;

import com.soriano.springboot.jpa.domain.model.Product;
import com.soriano.springboot.jpa.domain.service.ProductService;
import com.soriano.springboot.jpa.infrastructure.dao.entity.ProductEntity;
import com.soriano.springboot.jpa.infrastructure.dao.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
//import pe.interbank.icaa.rules.service.IcaaRuleEngine;


import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {


    private final ProductRepository productRepository ;

    @Override
    @Transactional(readOnly = true)
    public void save(Product product) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(product.getId());
        productEntity.setName(product.getName());
        productEntity.setPrice(product.getPrice());
        productRepository.save(productEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findAllProducts() {
        return productRepository.findAll().stream().map(entity -> Product.builder()
                .id(entity.getId())
                .name(entity.getName())
                .price(entity.getPrice())
                .build())
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Product findByIdProduct(Long id) throws Exception {
        ProductEntity productEntity = null;

             productEntity =  productRepository.findById(id).orElse(null);

             if(Objects.isNull(productEntity)) {
                 throw new Exception("Not found product");
             }
        return Product.builder()
                .id(productEntity.getId())
                .name(productEntity.getName())
                .price(productEntity.getPrice())
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }
}
