package com.nonxku.springbootmall.service;

import com.nonxku.springbootmall.dto.CreateOrderRequest;
import com.nonxku.springbootmall.model.Order;

public interface OrderService {


    Order getOrderById(Integer orderId);
    Integer createOrder (Integer userId, CreateOrderRequest createOrderRequest);
}
