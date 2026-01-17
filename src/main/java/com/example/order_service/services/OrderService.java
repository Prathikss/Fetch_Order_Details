package com.example.order_service.services;

import com.example.order_service.entity.OrderEntity;
import com.example.order_service.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository repository;

    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    // CREATE
    public OrderEntity createOrder(OrderEntity orderEntity) {
        logger.info("Creating order: {}", orderEntity.getOrderNo());
        return repository.save(orderEntity);
    }

    // GET BY ID
    public OrderEntity getOrder(String orderNo) {
        logger.info("Fetching order by ID: {}", orderNo);
        return repository.findByOrderNoIgnoreCase(orderNo.trim())
                .orElseThrow(() -> new RuntimeException("Order not found: " + orderNo));
    }

    // GET ALL
    public List<OrderEntity> getAllOrder() {
        logger.info("Fetching all orders");
        return repository.findAll();
    }

    // UPDATE
    public OrderEntity updateOrder(String orderNo, OrderEntity orderEntity) {
        logger.info("Updating order: {}", orderNo);
        OrderEntity existing = repository.findByOrderNoIgnoreCase(orderNo.trim())
                .orElseThrow(() -> new RuntimeException("Order not found: " + orderNo));

        existing.setCustomerName(orderEntity.getCustomerName());
        existing.setStatus(orderEntity.getStatus());
        existing.setTotalAmount(orderEntity.getTotalAmount());

        return repository.save(existing);
    }

    // DELETE
    public void deleteOrder(String orderNo) {
        logger.info("Deleting order: {}", orderNo);
        if (!repository.findByOrderNoIgnoreCase(orderNo.trim()).isPresent()) {
            throw new RuntimeException("Cannot delete. Order not found: " + orderNo);
        }
        repository.deleteById(orderNo.trim());
    }
}
