package com.kwatch.watch.dao;

import java.util.List;

import com.kwatch.watch.entity.Product;

public interface ProductDao {
    List<Product> allProduct();
    Product findProductByProductId(Long productId);
    Product findProductByProductName(String productName);
    Product findProductByProductTitle(String productTitle);
    List<Product> findProductsByCatagory(String catagory);
    void addProduct(Product product);
    void updateProduct(Product product);
    void deleteProduct(Long productId);
    
}
