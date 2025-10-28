package com.nonxku.springbootmall.service;


import com.nonxku.springbootmall.dto.ProductQueryParams;
import com.nonxku.springbootmall.dto.ProductRequest;
import com.nonxku.springbootmall.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> getProducts(ProductQueryParams productQueryParams);
    Product getProductById(Integer productId);
    Integer createProduct(ProductRequest productRequest);
    void updateProduct(Integer productId, ProductRequest productRequest);
    void deleteProductById(Integer productId);

}
