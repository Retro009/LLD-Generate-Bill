package com.scaler.repositories;

import com.scaler.models.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrderRepositoryImpl implements OrderRepository{
    List<Order> orders = new ArrayList<>();
    @Override
    public Order save(Order order) {
        orders.add(order);
        return order;
    }

    @Override
    public List<Order> findOrdersByCustomerSession(long customerSessionId) {
        return orders.stream().filter(order -> order.getCustomerSession().getId()==customerSessionId).collect(Collectors.toList());
    }
}
