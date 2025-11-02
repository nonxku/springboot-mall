package com.nonxku.springbootmall.dao;

import com.nonxku.springbootmall.dto.CreateOrderRequest;
import com.nonxku.springbootmall.dto.OrderQueryParams;
import com.nonxku.springbootmall.model.Order;
import com.nonxku.springbootmall.model.OrderItem;

import java.util.List;

public interface OrderDao {

    Integer countOrder(OrderQueryParams orderQueryParams);

    List<Order> getOrders(OrderQueryParams orderQueryParams);


    Order getOrderById(Integer orderId);

    List<OrderItem> getOrderItemsByOrderId(Integer orderId);

    Integer createOrder(Integer userId, Integer totalAmount);

    void createOrderItems(Integer orderId, List<OrderItem> orderItemList);
}
