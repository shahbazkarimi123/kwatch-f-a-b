package com.kwatch.watch.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "price")
    private Long price;

    @Column(name = "upload_date")
    private LocalDate uploadDate;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_title")
    private String productTitle;

    @Column(name = "brand_name")
    private String brandName;

    @Column(name = "product_vendor_name")
    private String productVendorName;

    @Column(name = "product_description")
    private String productDescription;

    @Column(name = "fabric")
    private String fabric;

    @Column(name = "material")
    private String material;

    @Column(name = "warranty")
    private Integer warranty;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "product_feedback", joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "feedback")
    private List<String> feedback = new ArrayList<>();

    @Column(name = "product_replace")
    private boolean productReplace;

    @Column(name = "product_return")
    private boolean productReturn;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "product_colors", joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "color")
    private List<String> productColor = new ArrayList<>();

    @Column(name = "product_color_details")
    private String productColorDetails;

    @Column(name = "category")
    private String category;

    @Column(name = "likes")
    private Integer likes;

    @Column(name = "discount")
    private Long discount;

    @Column(name = "product_length")
    private Double productLength;

    @Column(name = "product_breadth")
    private Double productBreadth;

    @Column(name = "product_height")
    private Double productHeight;

    @Column(name = "product_diagonal")
    private Double productDiagonal;

    @Column(name = "product_area")
    private Double productArea;

    @Column(name = "product_volume")
    private Double productVolume;

    @Column(name = "product_weight")
    private Double productWeight;

    @Column(name = "package_weight")
    private Double packageWeight;

    @Column(name = "offers")
    private String offers;

    @Column(name = "combo_product")
    private boolean comboProduct;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "combo_product_names", joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "combo_product_name")
    private List<String> comboProductName = new ArrayList<>();

    @Column(name = "cod")
    private boolean cod;

    @Lob
    @Column(name = "product_front_img", columnDefinition = "LONGBLOB")
    private byte[] productFrontImg;

    @Lob
    @Column(name = "product_back_img", columnDefinition = "LONGBLOB")
    private byte[] productBackImg;

    @Lob
    @Column(name = "product_side1_img", columnDefinition = "LONGBLOB")
    private byte[] productSide1Img;

    @Lob
    @Column(name = "product_side2_img", columnDefinition = "LONGBLOB")
    private byte[] productSide2Img;

    @Lob
    @Column(name = "product_above_img", columnDefinition = "LONGBLOB")
    private byte[] productAboveImg;

    @Transient
    private String productFrontImgBase64;
    @Transient
    private String productBackImgBase64;
    @Transient
    private String productSide1ImgBase64;
    @Transient
    private String productSide2ImgBase64;
    @Transient
    private String productAboveImgBase64;

    public Product() {
    }

    public Product(Long productId, Long price, LocalDate uploadDate, String productName, String productTitle,
            String brandName, String productVendorName, String productDescription, String fabric, String material,
            Integer warranty, List<String> feedback, boolean productReplace, boolean productReturn,
            List<String> productColor, String productColorDetails, String category, Integer likes, Long discount,
            Double productLength, Double productBreadth, Double productHeight, Double productDiagonal,
            Double productArea,
            Double productVolume, Double productWeight, Double packageWeight, String offers, boolean comboProduct,
            List<String> comboProductName, boolean cod, byte[] productFrontImg, byte[] productBackImg,
            byte[] productSide1Img, byte[] productSide2Img, byte[] productAboveImg) {
        this.productId = productId;
        this.price = price;
        this.uploadDate = uploadDate;
        this.productName = productName;
        this.productTitle = productTitle;
        this.brandName = brandName;
        this.productVendorName = productVendorName;
        this.productDescription = productDescription;
        this.fabric = fabric;
        this.material = material;
        this.warranty = warranty;
        this.feedback = feedback;
        this.productReplace = productReplace;
        this.productReturn = productReturn;
        this.productColor = productColor;
        this.productColorDetails = productColorDetails;
        this.category = category;
        this.likes = likes;
        this.discount = discount;
        this.productLength = productLength;
        this.productBreadth = productBreadth;
        this.productHeight = productHeight;
        this.productDiagonal = productDiagonal;
        this.productArea = productArea;
        this.productVolume = productVolume;
        this.productWeight = productWeight;
        this.packageWeight = packageWeight;
        this.offers = offers;
        this.comboProduct = comboProduct;
        this.comboProductName = comboProductName;
        this.cod = cod;
        this.productFrontImg = productFrontImg;
        this.productBackImg = productBackImg;
        this.productSide1Img = productSide1Img;
        this.productSide2Img = productSide2Img;
        this.productAboveImg = productAboveImg;
    }

    @Transient
    public String getProductFrontImgBase64() {
        return this.productFrontImgBase64;
    }

    @Transient
    public String getProductBackImgBase64() {

        return this.productBackImgBase64;
    }

    @Transient
    public String getProductSide1ImgBase64() {

        return this.productSide1ImgBase64;
    }

    @Transient
    public String getProductSide2ImgBase64() {

        return this.productSide2ImgBase64;
    }

    @Transient
    public String getProductAboveImgBase64() {

        return this.productAboveImgBase64;

    }

    @Transient
    public void setProductFrontImgBase64(String productFrontImgBase64) {
        this.productFrontImgBase64 = productFrontImgBase64;
    }

    @Transient
    public void setProductBackImgBase64(String productBackImgBase64) {

        this.productBackImgBase64 = productBackImgBase64;
    }

    @Transient
    public void setProductSide1ImgBase64(String productSide1ImgBase64) {

        this.productSide1ImgBase64 = productSide1ImgBase64;
    }

    @Transient
    public void setProductSide2ImgBase64(String productSide2ImgBase64) {

        this.productSide2ImgBase64 = productSide2ImgBase64;
    }

    @Transient
    public void setProductAboveImgBase64(String productAboveImgBase64) {

        this.productAboveImgBase64 = productAboveImgBase64;

    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public LocalDate getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(LocalDate uploadDate) {
        this.uploadDate = uploadDate;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getProductVendorName() {
        return productVendorName;
    }

    public void setProductVendorName(String productVendorName) {
        this.productVendorName = productVendorName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescripton) {
        this.productDescription = productDescripton;
    }

    public String getFabric() {
        return fabric;
    }

    public void setFabric(String fabric) {
        this.fabric = fabric;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public Integer getWarranty() {
        return warranty;
    }

    public void setWarranty(Integer warranty) {
        this.warranty = warranty;
    }

    public List<String> getFeedback() {
        return feedback;
    }

    public void setFeedback(List<String> feedback) {
        this.feedback = feedback;
    }

    public boolean isProductReplace() {
        return productReplace;
    }

    public void setProductReplace(boolean productReplace) {
        this.productReplace = productReplace;
    }

    public boolean isProductReturn() {
        return productReturn;
    }

    public void setProductReturn(boolean productReturn) {
        this.productReturn = productReturn;
    }

    public List<String> getProductColor() {
        return productColor;
    }

    public void setProductColor(List<String> productColor) {
        this.productColor = productColor;
    }

    public String getProductColorDetails() {
        return productColorDetails;
    }

    public void setProductColorDetails(String productColorDetails) {
        this.productColorDetails = productColorDetails;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Long getDiscount() {
        return discount;
    }

    public void setDiscount(Long discount) {
        this.discount = discount;
    }

    public Double getProductLength() {
        return productLength;
    }

    public void setProductLength(Double productLength) {
        this.productLength = productLength;
    }

    public Double getProductBreadth() {
        return productBreadth;
    }

    public void setProductBreadth(Double productBreadth) {
        this.productBreadth = productBreadth;
    }

    public Double getProductHeight() {
        return productHeight;
    }

    public void setProductHeight(Double productHeight) {
        this.productHeight = productHeight;
    }

    public Double getProductDiagonal() {
        return productDiagonal;
    }

    public void setProductDiagonal(Double productDiagonal) {
        this.productDiagonal = productDiagonal;
    }

    public Double getProductArea() {
        return productArea;
    }

    public void setProductArea(Double productArea) {
        this.productArea = productArea;
    }

    public Double getProductVolume() {
        return productVolume;
    }

    public void setProductVolume(Double productVolume) {
        this.productVolume = productVolume;
    }

    public Double getProductWeight() {
        return productWeight;
    }

    public void setProductWeight(Double productWeight) {
        this.productWeight = productWeight;
    }

    public Double getPackageWeight() {
        return packageWeight;
    }

    public void setPackageWeight(Double packageWeight) {
        this.packageWeight = packageWeight;
    }

    public String getOffers() {
        return offers;
    }

    public void setOffers(String offers) {
        this.offers = offers;
    }

    public boolean isComboProduct() {
        return comboProduct;
    }

    public void setComboProduct(boolean comboProduct) {
        this.comboProduct = comboProduct;
    }

    public List<String> getComboProductName() {
        return comboProductName;
    }

    public void setComboProductName(List<String> comboProductName) {
        this.comboProductName = comboProductName;
    }

    public boolean isCod() {
        return cod;
    }

    public void setCod(boolean cod) {
        this.cod = cod;
    }

    public byte[] getProductFrontImg() {
        return productFrontImg;
    }

    public void setProductFrontImg(byte[] productFrontImg) {
        this.productFrontImg = productFrontImg;
    }

    public byte[] getProductBackImg() {
        return productBackImg;
    }

    public void setProductBackImg(byte[] productBackImg) {
        this.productBackImg = productBackImg;
    }

    public byte[] getProductSide1Img() {
        return productSide1Img;
    }

    public void setProductSide1Img(byte[] productSide1Img) {
        this.productSide1Img = productSide1Img;
    }

    public byte[] getProductSide2Img() {
        return productSide2Img;
    }

    public void setProductSide2Img(byte[] productSide2Img) {
        this.productSide2Img = productSide2Img;
    }

    public byte[] getProductAboveImg() {
        return productAboveImg;
    }

    public void setProductAboveImg(byte[] productAboveImg) {
        this.productAboveImg = productAboveImg;
    }

    @Override
    public String toString() {
        return "Product [productId=" + productId + ", price=" + price + ", uploadDate=" + uploadDate + ", productName="
                + productName + ", productTitle=" + productTitle + ", brandName=" + brandName + ", productVendorName="
                + productVendorName + ", productDescription=" + productDescription + ", fabric=" + fabric
                + ", material=" + material + ", warranty=" + warranty + ", feedback=" + feedback + ", productReplace="
                + productReplace + ", productReturn=" + productReturn + ", productColor=" + productColor
                + ", productColorDetails=" + productColorDetails + ", category=" + category + ", likes=" + likes
                + ", discount=" + discount + ", productLength=" + productLength + ", productBreadth=" + productBreadth
                + ", productHeight=" + productHeight + ", productDiagonal=" + productDiagonal + ", productArea="
                + productArea + ", productVolume=" + productVolume + ", productWeight=" + productWeight
                + ", packageWeight=" + packageWeight + ", offers=" + offers + ", comboProduct=" + comboProduct
                + ", comboProductName=" + comboProductName + ", cod=" + cod + ", productFrontImg="
                + Arrays.toString(productFrontImg) + ", productBackImg=" + Arrays.toString(productBackImg)
                + ", productSide1Img=" + Arrays.toString(productSide1Img) + ", productSide2Img="
                + Arrays.toString(productSide2Img) + ", productAboveImg=" + Arrays.toString(productAboveImg) + "]";
    }
}
