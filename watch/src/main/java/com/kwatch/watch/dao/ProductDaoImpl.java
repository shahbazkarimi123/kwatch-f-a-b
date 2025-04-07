package com.kwatch.watch.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kwatch.watch.entity.Product;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class ProductDaoImpl implements ProductDao{

    private final EntityManager entityManager;
    
    public ProductDaoImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public List<Product> allProduct() {
        TypedQuery<Product> query = entityManager.createQuery("SELECT p FROM Product p",Product.class);
        return query.getResultList();
    }

    @Override
    public Product findProductByProductId(Long productId) {
        Product pr = entityManager.find(Product.class, productId);
        
        
        return pr;
    }

    @Override
    public Product findProductByProductName(String productName) {
        TypedQuery<Product> query = entityManager
            .createQuery("SELECT p FROM Product p WHERE p.productName = :productName",
                Product.class);
        query.setParameter("productName", productName);
        return query.getResultStream().findFirst().orElse(null);
    }

    @Override
    public Product findProductByProductTitle(String productTitle) {
        TypedQuery<Product> query = entityManager.createQuery(
            "SELECT p FROM Product p WHERE p.productTitle = :productTitle",
            Product.class
        );
        query.setParameter("productTitle", productTitle);
        return query.getResultStream().findFirst().orElse(null);
    }

    @Override
    public List<Product> findProductsByCatagory(String catagory) {
        TypedQuery<Product> query = entityManager.createQuery(
            "SELECT p FROM Product p WHERE p.catagory = :catagory",
            Product.class
        );
        query.setParameter("catagory", catagory);
        return query.getResultList();
    }

    @Override
    public void addProduct(Product product) {
        entityManager.persist(product);
    }

    @Override
    public void updateProduct(Product product) {
        entityManager.merge(product);
    }

    @Override
    public void deleteProduct(Long productId) {
        Product tempProduct = entityManager.find(Product.class, productId);
        if(tempProduct != null){
        
            entityManager.remove(tempProduct);
        }

    }

}
