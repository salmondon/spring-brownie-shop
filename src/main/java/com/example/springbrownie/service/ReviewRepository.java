package com.example.springbrownie.service;

import com.example.springbrownie.entity.OrderDetail;
import com.example.springbrownie.entity.Review;
import org.springframework.data.repository.CrudRepository;

public interface ReviewRepository extends CrudRepository<Review, Long> {
    Iterable<Review> findByProductId(long id);
}
