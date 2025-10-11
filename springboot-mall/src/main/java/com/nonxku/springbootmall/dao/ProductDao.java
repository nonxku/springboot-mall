package com.nonxku.springbootmall.dao;

import com.nonxku.springbootmall.dto.ProductRequest;
import com.nonxku.springbootmall.model.Product;

public interface ProductDao {

    Product getProductById(Integer productId);
    Integer createProduct(ProductRequest productRequest);
    void updateProduct(Integer productId, ProductRequest productRequest);

}
