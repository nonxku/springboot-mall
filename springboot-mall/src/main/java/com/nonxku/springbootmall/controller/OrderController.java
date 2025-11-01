package com.nonxku.springbootmall.controller;


import com.nonxku.springbootmall.dto.CreateOrderRequest;
import com.nonxku.springbootmall.model.Order;
import com.nonxku.springbootmall.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;


    //先有帳號->再有訂單 （訂單是帳號的附屬資料）
    @PostMapping("/users/{userId}/orders")
    public ResponseEntity<?> createOrder(@PathVariable Integer userId,
                                         @RequestBody @Valid CreateOrderRequest createOrderRequest){

       Integer orderId = orderService.createOrder(userId,createOrderRequest);

       return ResponseEntity.status(HttpStatus.CREATED).body(orderId);

    }

}
