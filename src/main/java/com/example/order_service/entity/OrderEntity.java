package com.example.order_service.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "order_details")
public class OrderEntity {

    @Id
    @Column(name = "order_no", nullable = false, length = 50)
    private String orderNo;   // String, matches Cloud SQL

    @Column(name = "status", length = 50)
    private String status;

    @Column(name = "customer_name", length = 100)
    private String customerName;

    @Column(name = "total_amount")
    private double totalAmount;

    public OrderEntity() {}

    public OrderEntity(String orderNo, String status, String customerName, double totalAmount) {
        this.orderNo = orderNo;
        this.status = status;
        this.customerName = customerName;
        this.totalAmount = totalAmount;
    }

    public String getOrderNo() {
        return orderNo;
    }
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public double getTotalAmount() {
        return totalAmount;
    }
    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
