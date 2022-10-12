package com.example.springbrownie.service;

import com.example.springbrownie.entity.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
