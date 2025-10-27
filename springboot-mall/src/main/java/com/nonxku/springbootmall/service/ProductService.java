package com.nonxku.springbootmall.service;

import com.nonxku.springbootmall.constant.ProductCategory;
import com.nonxku.springbootmall.dto.ProductRequest;
import com.nonxku.springbootmall.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> getProducts(ProductCategory category,String search);
    Product getProductById(Integer productId);
    Integer createProduct(ProductRequest productRequest);
    void updateProduct(Integer productId, ProductRequest productRequest);
    void deleteProductById(Integer productId);

}
