package com.example.order_service.repository;

import com.example.order_service.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<OrderEntity, String> {

    // Case-insensitive lookup to avoid GET failures due to case
    @Query("SELECT o FROM OrderEntity o WHERE LOWER(o.orderNo) = LOWER(:orderNo)")
    Optional<OrderEntity> findByOrderNoIgnoreCase(@Param("orderNo") String orderNo);
}
