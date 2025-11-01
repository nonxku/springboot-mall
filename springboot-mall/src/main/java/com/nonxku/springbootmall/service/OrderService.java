package com.nonxku.springbootmall.service;

import com.nonxku.springbootmall.dto.CreateOrderRequest;

public interface OrderService {

    Integer createOrder (Integer userId, CreateOrderRequest createOrderRequest);
}
