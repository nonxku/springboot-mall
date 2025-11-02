package com.nonxku.springbootmall.service;

import com.nonxku.springbootmall.dto.CreateOrderRequest;
import com.nonxku.springbootmall.dto.OrderQueryParams;
import com.nonxku.springbootmall.model.Order;

import java.util.List;

public interface OrderService {


    Order getOrderById(Integer orderId);
    Integer createOrder (Integer userId, CreateOrderRequest createOrderRequest);


    Integer countOrder(OrderQueryParams orderQueryParams);
    List<Order> getOrders(OrderQueryParams orderQueryParams);


}
