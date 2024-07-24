package com.scaler.services;

import com.scaler.exceptions.CustomerSessionNotFound;
import com.scaler.models.*;
import com.scaler.repositories.CustomerSessionRepository;
import com.scaler.repositories.OrderRepository;

import java.util.List;
import java.util.Map;

public class OrderServiceImpl implements OrderService{
    CustomerSessionRepository customerSessionRepository;
    OrderRepository orderRepository;
    public OrderServiceImpl(CustomerSessionRepository customerSessionRepository, OrderRepository orderRepository){
        this.customerSessionRepository = customerSessionRepository;
        this.orderRepository = orderRepository;
    }
    @Override
    public Bill generateBill(long userId) throws CustomerSessionNotFound {
        CustomerSession session = customerSessionRepository.findActiveCustomerSessionByUserId(userId).orElseThrow(()-> new CustomerSessionNotFound("Active Session Not Found"));
        List<Order> customerOrders = orderRepository.findOrdersByCustomerSession(session.getId());
        double totalItemCost = 0;
        for(Order order:customerOrders){
            for(Map.Entry<MenuItem,Integer> entry:order.getOrderedItems().entrySet()){
                totalItemCost += entry.getKey().getPrice()* entry.getValue();
            }
        }
        double gst = 0.05 * totalItemCost;
        double serviceCharge = 0.1 * totalItemCost;

        double totalCost = totalItemCost + gst + serviceCharge;

        Bill bill = new Bill();
        bill.setGst(gst);
        bill.setServiceCharge(serviceCharge);
        bill.setTotalAmount(totalCost);

        session.setCustomerSessionStatus(CustomerSessionStatus.ENDED);
        return bill;
    }
}
