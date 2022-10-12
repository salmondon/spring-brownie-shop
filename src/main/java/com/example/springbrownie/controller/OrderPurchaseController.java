package com.example.springbrownie.controller;

import com.example.springbrownie.entity.OrderPurchase;
import com.example.springbrownie.service.OrderPurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class OrderPurchaseController {
    @Autowired
    private OrderPurchaseRepository orderPurchaseRepository;

    @GetMapping(path = "/order")
    public Iterable<OrderPurchase> getAllOrderPurchases() {
        return orderPurchaseRepository.findAll();
    }

    @GetMapping(path = "/order/{id}")
    public ResponseEntity<OrderPurchase> getOrderPurchaseById(@PathVariable("id") long id) {
        Optional<OrderPurchase> orderPurchaseData = orderPurchaseRepository.findById(id);

        if (orderPurchaseData.isPresent()) {
            return new ResponseEntity<>(orderPurchaseData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(path = "/order")
    public ResponseEntity<OrderPurchase> createOrderPurchase(@RequestBody OrderPurchase orderPurchase) {
        OrderPurchase o = new OrderPurchase();
        o.setPrice(orderPurchase.getPrice());
        o.setTel(orderPurchase.getTel());

        orderPurchaseRepository.save(o);
        return new ResponseEntity<>(o, HttpStatus.CREATED);
    }
}
