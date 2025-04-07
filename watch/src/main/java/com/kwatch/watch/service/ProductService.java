package com.kwatch.watch.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.kwatch.watch.entity.Product;

public interface ProductService {
    List<Product> allProduct();
    Product findProductByProductId(Long productId);
    Product findProductByProductName(String productName);
    Product findProductByProductTitle(String productTitle);
    List<Product> findProductsByCatagory(String catagory);
    void addProduct(Product product,MultipartFile frontImg,MultipartFile backImg,MultipartFile side1Img,MultipartFile side2Img,MultipartFile aboveImg) throws IOException;
    void updateProduct(Product product);
    void deleteProduct(Long productId);
}
