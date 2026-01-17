package com.example.order_service.controller;

import com.example.order_service.entity.OrderEntity;
import com.example.order_service.services.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")  // lowercase recommended
@CrossOrigin(origins = "*")
public class OrderController {

    private final OrderService service;
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    public OrderController(OrderService service) {
        this.service = service;
    }

    // POST: create a new order
    @PostMapping
    public OrderEntity create(@RequestBody OrderEntity order) {
        logger.info("POST /orders called with orderNo: {}", order.getOrderNo());
        return service.createOrder(order);
    }

    // GET ONE: get by orderNo
    @GetMapping("/{orderNo}")
    public OrderEntity getById(@PathVariable String orderNo) {
        logger.info("GET /orders/{} called", orderNo);
        return service.getOrder(orderNo);
    }

    // GET ALL: get all orders
    @GetMapping
    public List<OrderEntity> getAll() {
        logger.info("GET /orders called");
        return service.getAllOrder();
    }

    // PUT: update an order
    @PutMapping("/{orderNo}")
    public OrderEntity update(@PathVariable String orderNo, @RequestBody OrderEntity order) {
        logger.info("PUT /orders/{} called", orderNo);
        return service.updateOrder(orderNo, order);
    }

    // DELETE: delete an order
    @DeleteMapping("/{orderNo}")
    public String delete(@PathVariable String orderNo) {
        logger.info("DELETE /orders/{} called", orderNo);
        service.deleteOrder(orderNo);
        return "Order " + orderNo + " deleted successfully.";
    }
}
