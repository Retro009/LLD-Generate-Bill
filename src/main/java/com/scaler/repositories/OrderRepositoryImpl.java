package com.scaler.repositories;

import com.scaler.models.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrderRepositoryImpl implements OrderRepository{
    List<Order> orders = new ArrayList<>();
    private static long idCounter = 0;
    @Override
    public Order save(Order order) {
        orders.add(order);
        if(order.getId()==0)
            order.setId(idCounter++);
        return order;
    }

    @Override
    public List<Order> findOrdersByCustomerSession(long customerSessionId) {
        return orders.stream().filter(order -> order.getCustomerSession().getId()==customerSessionId).collect(Collectors.toList());
    }
}
