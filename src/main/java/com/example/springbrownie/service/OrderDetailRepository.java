package com.example.springbrownie.service;

import com.example.springbrownie.entity.OrderDetail;
import org.springframework.data.repository.CrudRepository;

public interface OrderDetailRepository extends CrudRepository<OrderDetail, Long> {
    Iterable<OrderDetail> findByOrderPurchaseId(long id);
}
