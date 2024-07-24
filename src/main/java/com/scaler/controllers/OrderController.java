package com.scaler.controllers;

import com.scaler.dtos.*;
import com.scaler.exceptions.CustomerSessionNotFound;
import com.scaler.models.Bill;
import com.scaler.models.Order;
import com.scaler.services.OrderService;

public class OrderController {

    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    public GenerateBillResponseDto generateBill(GenerateBillRequestDto requestDto){
        GenerateBillResponseDto responseDto =  new GenerateBillResponseDto();
        try {
            responseDto.setBill(orderService.generateBill(requestDto.getUserId()));
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        } catch (CustomerSessionNotFound e) {
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
        }
        return responseDto;
    }


}
