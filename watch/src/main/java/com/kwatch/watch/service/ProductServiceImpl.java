package com.kwatch.watch.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kwatch.watch.dao.ProductDao;
import com.kwatch.watch.entity.Product;

import jakarta.transaction.Transactional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;
    private final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Override
    public List<Product> allProduct() {
        logger.info("Logged in service allProduct *********");
        return productDao.allProduct();
    }

    @Override
    public Product findProductByProductId(Long productId) {
        return productDao.findProductByProductId(productId);
    }

    @Override
    public Product findProductByProductName(String productName) {
        return productDao.findProductByProductName(productName);
    }

    @Override
    public Product findProductByProductTitle(String productTitle) {
        return productDao.findProductByProductTitle(productTitle);
    }

    @Override
    public List<Product> findProductsByCatagory(String catagory) {
        return productDao.findProductsByCatagory(catagory);
    }

    @Override
    @Transactional
    public void addProduct(
            Product product,
            MultipartFile frontImg,
            MultipartFile backImg,
            MultipartFile side1Img,
            MultipartFile side2Img,
            MultipartFile aboveImg) throws IOException {
                
                product.setUploadDate(LocalDate.now());
                try{
        
            if (frontImg != null && !frontImg.isEmpty())
                product.setProductFrontImg(frontImg.getBytes());
            if (backImg != null && !backImg.isEmpty())
                product.setProductBackImg(backImg.getBytes());
            if (side1Img != null && !side1Img.isEmpty())
                product.setProductSide1Img(side1Img.getBytes());
            if (side2Img != null && side2Img.isEmpty())
                product.setProductSide2Img(side2Img.getBytes());
            if (aboveImg != null && !aboveImg.isEmpty())
                product.setProductAboveImg(aboveImg.getBytes());
            Double length = product.getProductLength();
            Double breadth = product.getProductBreadth();
            Double height = product.getProductHeight();
            // area of cuboid
            Double area = areaOfCuboid(length, breadth, height);
            product.setProductArea(area);
            // volume of cuboid
            Double volume = volumeOfCuboid(length, breadth, height);
            product.setProductVolume(volume);


            // diagonal of cuboid

            product.setProductDiagonal(diagonalOfCuboid(length, breadth, height));
        

        productDao.addProduct(product);
    }catch(IOException e){
        logger.info("Error while processing product images", e);
        throw new RuntimeException("Failed to process product images", e);
    }

    }

    @Override
    @Transactional
    public void updateProduct(Product product) {
        if (product.getProductLength() != null && product.getProductBreadth() != null
                && product.getProductHeight() != null) {

            Double length = product.getProductLength();
            Double breadth = product.getProductBreadth();
            Double height = product.getProductHeight();
            // area of cuboid
            Double area = areaOfCuboid(length, breadth, height);
            product.setProductArea(area);
            // volume of cuboid
            Double volume = volumeOfCuboid(length, breadth, height);
            product.setProductVolume(volume);

            // diagonal of cuboid

            product.setProductDiagonal(diagonalOfCuboid(length, breadth, height));
        }
        productDao.updateProduct(product);

    }

    @Override
    @Transactional
    public void deleteProduct(Long productId) {
        productDao.deleteProduct(productId);
    }

    private Double areaOfCuboid(Double length, Double breadth, double height) {
        Double area = 2 * ((length * breadth) + (breadth * height) + (height * length));
        return area;
    }

    private Double volumeOfCuboid(Double length, Double breadth, double height) {
        return length * height * breadth;
    }

    private Double diagonalOfCuboid(Double length, Double breadth, double height) {
        Double sumSquareOfAllSides = (length * length) + (breadth * breadth) + (height * height);
        return Math.sqrt(sumSquareOfAllSides);
    }

}
