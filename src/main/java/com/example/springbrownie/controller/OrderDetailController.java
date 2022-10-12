package com.example.springbrownie.controller;

import com.example.springbrownie.entity.OrderDetail;
import com.example.springbrownie.entity.OrderPurchase;
import com.example.springbrownie.entity.Product;
import com.example.springbrownie.service.OrderDetailRepository;
import com.example.springbrownie.service.OrderPurchaseRepository;
import com.example.springbrownie.service.ProductRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class OrderDetailController {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderPurchaseRepository orderPurchaseRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @GetMapping(path = "/order/{orderId}/detail")
    public ResponseEntity<Iterable<OrderDetail>> getAllOrderDetailsByOrderPurchaseId(@PathVariable(value = "orderId") long orderId) {
        if (!orderPurchaseRepository.existsById(orderId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        Iterable<OrderDetail> orderDetails = orderDetailRepository.findByOrderPurchaseId(orderId);
        return new ResponseEntity<>(orderDetails, HttpStatus.OK);
    }

    @PostMapping(path = "/order/{orderId}/detail")
    public ResponseEntity<OrderDetail> createOrderDetail(@PathVariable(value = "orderId") long orderId, @RequestBody OrderDetail orderDetail) {
        Optional<OrderPurchase> o = orderPurchaseRepository.findById(orderId);
        if (o.isPresent()) {
            orderDetail.setOrderPurchase(o.get());
            orderDetailRepository.save(orderDetail);
            return new ResponseEntity<>(orderDetail, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
