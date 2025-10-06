package com.nonxku.springbootmall.dao;

import com.nonxku.springbootmall.model.Product;

public interface ProductDao {

    Product getProductById(Integer productId);

}
