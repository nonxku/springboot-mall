package com.nonxku.springbootmall.controller;

import com.nonxku.springbootmall.dto.ProductRequest;
import com.nonxku.springbootmall.model.Product;
import com.nonxku.springbootmall.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable Integer productId) {

        Product product = productService.getProductById(productId);

        if(product != null){
            return ResponseEntity.status(HttpStatus.OK).body(product);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/products")
    public ResponseEntity<Product> createProducts(@RequestBody @Valid ProductRequest productRequest) {

        Integer productId = productService.createProduct(productRequest);
        Product product = productService.getProductById(productId);

        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @PutMapping("/products/{productId}")
    public ResponseEntity<Product> updateProducts(@PathVariable Integer productId,
                                                  @RequestBody @Valid ProductRequest productRequest) {

        //先檢查product是否存在
        Product product=productService.getProductById(productId);
        if(product==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            //確認存在後則修改商品資訊
            productService.updateProduct(productId, productRequest);
            Product updatedproduct = productService.getProductById(productId);
            return ResponseEntity.status(HttpStatus.OK).body(updatedproduct);
        }
    }



}
