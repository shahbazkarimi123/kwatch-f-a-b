package com.kwatch.watch.restController;

import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kwatch.watch.entity.Product;
import com.kwatch.watch.service.ProductService;


// @CrossOrigin(origins="http://192.168.1.13:4200")
@RestController
public class ProductRestController {
    @Autowired
    private ProductService service;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private Logger logger = LoggerFactory.getLogger(ProductRestController.class);

    @GetMapping(path = "/products")
    public List<Product> allProducts() {

        return service.allProduct().stream()
                .peek(product -> {
                    if (product.getProductFrontImg() != null) {
                        product.setProductFrontImgBase64(
                                Base64.getEncoder().encodeToString(product.getProductFrontImg()));
                    }
                    if (product.getProductBackImg() != null) {
                        product.setProductBackImgBase64(
                                Base64.getEncoder().encodeToString(product.getProductBackImg()));
                    }
                    if (product.getProductSide1Img() != null) {
                        product.setProductSide1ImgBase64(
                                Base64.getEncoder().encodeToString(product.getProductSide1Img()));
                    }
                    if (product.getProductSide2Img() != null) {
                        product.setProductSide2ImgBase64(
                                Base64.getEncoder().encodeToString(product.getProductSide2Img()));
                    }
                    if (product.getProductAboveImg() != null) {
                        product.setProductAboveImgBase64(
                                Base64.getEncoder().encodeToString(product.getProductAboveImg()));
                    }

                }).collect(Collectors.toList());
    }

    // if (product.getProductFrontImg() != null) {
    // product.setProductFrontImgBase64(Base64.getEncoder().encodeToString(product.getProductFrontImg()));
    // }

    @GetMapping(path = "/products/{productId}")
    public Product getUserByProductId(@PathVariable Long productId) {
        // System.out.println(new BCryptPasswordEncoder().encode("karimi"));

        Product product=service.findProductByProductId(productId);
        if(product.getProductFrontImg() !=null){
            product.setProductFrontImgBase64(Base64.getEncoder().encodeToString(product.getProductFrontImg()));
        }

        
        return product;

    }

    @PostMapping(path = "/products", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Map<String, String>> createProduct(
            @RequestPart("product") String productJson,
            @RequestPart(value = "productFrontImg", required = false) MultipartFile frontImg,
            @RequestPart(value = "productBackImg", required = false) MultipartFile backImg,
            @RequestPart(value = "productSide1Img", required = false) MultipartFile side1Img,
            @RequestPart(value = "productSide2Img", required = false) MultipartFile side2Img,
            @RequestPart(value = "productAboveImg", required = false) MultipartFile aboveImg) throws IOException {
        try {
            Product product = objectMapper.readValue(productJson, Product.class);
            service.addProduct(product, frontImg, backImg, side1Img, side2Img, aboveImg);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Product created successfully");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (IOException e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Someting went wrong");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }

    }
    @GetMapping(path="/products/delete/{productId}")
    public void deleteProductById(@PathVariable Long productId){
        service.deleteProduct(productId);
    }

}
