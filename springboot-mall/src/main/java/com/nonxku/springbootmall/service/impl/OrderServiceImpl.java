package com.nonxku.springbootmall.service.impl;

import com.nonxku.springbootmall.dao.OrderDao;
import com.nonxku.springbootmall.dao.ProductDao;
import com.nonxku.springbootmall.dto.BuyItem;
import com.nonxku.springbootmall.dto.CreateOrderRequest;
import com.nonxku.springbootmall.model.Order;
import com.nonxku.springbootmall.model.OrderItem;
import com.nonxku.springbootmall.model.Product;
import com.nonxku.springbootmall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;
    @Autowired
    private ProductDao productDao;


    @Override
    public Order getOrderById(Integer orderId) {

        Order order = orderDao.getOrderById(orderId);

        List<OrderItem> orderItemList = orderDao.getOrderItemsByOrderId(orderId);

        order.setOrderItemList(orderItemList);

        return order;
    }

    @Transactional
    @Override
    public Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest) {


        int totalAmount =0;
        List<OrderItem> orderItemList = new ArrayList<>();

        for(BuyItem buyItem : createOrderRequest.getBuyItemList()){
            Product product = productDao.getProductById(buyItem.getProductId());

            //計算訂單總花費
            int amount = buyItem.getQuantity() * product.getPrice();
            totalAmount = totalAmount + amount;

            //將前端傳的 BuyItem 轉換成 OrderItem
            OrderItem orderItem = new OrderItem();
            orderItem.setProductId(buyItem.getProductId());
            orderItem.setQuantity(buyItem.getQuantity());
            orderItem.setAmount(amount);


            orderItemList.add(orderItem);

        }

        //創建訂單：分別在order & orderId 兩張表格內建立數據

        Integer orderId = orderDao.createOrder(userId,totalAmount);
        orderDao.createOrderItems(orderId,orderItemList);
        return orderId;
    }


}
