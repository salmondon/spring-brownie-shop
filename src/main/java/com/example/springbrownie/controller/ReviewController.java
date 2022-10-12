package com.example.springbrownie.controller;

import com.example.springbrownie.entity.OrderDetail;
import com.example.springbrownie.entity.OrderPurchase;
import com.example.springbrownie.entity.Product;
import com.example.springbrownie.entity.Review;
import com.example.springbrownie.service.ProductRepository;
import com.example.springbrownie.service.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class ReviewController {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ReviewRepository reviewRepository;

    @GetMapping(path = "/product/{productId}/review")
    public ResponseEntity<Iterable<Review>> getAllOrderDetailsByOrderPurchaseId(@PathVariable(value = "productId") long productId) {
        if (!productRepository.existsById(productId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        Iterable<Review> reviews = reviewRepository.findByProductId(productId);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @PostMapping(path = "/product/{productId}/review")
    public ResponseEntity<Review> createReview(@PathVariable(value = "productId") long productId, @RequestBody Review review) {
        Optional<Product> p = productRepository.findById(productId);
        if (p.isPresent()) {
            review.setProduct(p.get());
            reviewRepository.save(review);
            return new ResponseEntity<>(review, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
