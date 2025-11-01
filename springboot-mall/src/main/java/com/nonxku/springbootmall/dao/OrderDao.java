package com.nonxku.springbootmall.dao;

import com.nonxku.springbootmall.dto.CreateOrderRequest;
import com.nonxku.springbootmall.model.OrderItem;

import java.util.List;

public interface OrderDao {

    Integer createOrder(Integer userId, Integer totalAmount);

    void createOrderItems(Integer orderId, List<OrderItem> orderItemList);
}
