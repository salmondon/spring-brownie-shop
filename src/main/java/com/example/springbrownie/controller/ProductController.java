package com.example.springbrownie.controller;

import com.example.springbrownie.entity.Product;
import com.example.springbrownie.service.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @PostMapping(path = "/product/add")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        Product p = new Product();
        p.setName(product.getName());
        p.setDescr(product.getDescr());
        p.setPrice(product.getPrice());
        p.setUrl(product.getUrl());

        productRepository.save(p);
        return new ResponseEntity<>(p, HttpStatus.CREATED);
    }

    @GetMapping(path = "/product")
    public Iterable<Product> getProducts() {
        return productRepository.findAll();
    }

    @GetMapping(path = "/product/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") long id) {
        Optional<Product> productData = productRepository.findById(id);
        if (productData.isPresent()) {
            return new ResponseEntity<>(productData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = "/get")
    public String test() {
        return "TEST GET";
    }
}
