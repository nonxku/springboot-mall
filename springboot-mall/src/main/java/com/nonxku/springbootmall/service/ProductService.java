package com.nonxku.springbootmall.service;

import com.nonxku.springbootmall.dto.ProductRequest;
import com.nonxku.springbootmall.model.Product;

public interface ProductService {

    Product getProductById(Integer productId);
    Integer createProduct(ProductRequest productRequest);
    void updateProduct(Integer productId, ProductRequest productRequest);

}
