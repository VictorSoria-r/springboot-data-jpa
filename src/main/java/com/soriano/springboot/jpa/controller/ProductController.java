package com.soriano.springboot.jpa.controller;


import com.soriano.springboot.jpa.domain.model.Product;
import com.soriano.springboot.jpa.domain.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/list")
    public List<Product> productList() {
        return productService.findAllProducts();
    }

    @GetMapping("/{id}/data")
    public Product getProduct(@PathVariable Long id) {
        try {
            return  productService.findByIdProduct(id);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return  new Product();
        }
    }

    @DeleteMapping("{id}/delete")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProductById(id);
    }

}
