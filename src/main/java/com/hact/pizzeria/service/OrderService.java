package com.hact.pizzeria.service;

import com.hact.pizzeria.persistence.entity.OrderEntity;
import com.hact.pizzeria.persistence.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private static final String DELIVERY = "D";
    private static final String CARRYOUT = "C";
    private static final String ON_SITE = "S";

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<OrderEntity> getAll() {
        return orderRepository.findAll();
    }

    public List<OrderEntity> getTodayOrders() {
        return orderRepository.findAllByDateAfter(LocalDate.now().atTime(0, 0));
    }

    public List<OrderEntity> getOutsideOrders() {
        return orderRepository.findAllByMethodIn(List.of(DELIVERY, CARRYOUT));
    }
}
