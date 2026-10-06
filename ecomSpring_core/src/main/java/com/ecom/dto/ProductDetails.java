package com.ecom.dto;

public class ProductDetails {
    private int productId;
    private String productName;
    private double price;
    private int stock;

    private String categoryName;
    private String vendorName;

    public ProductDetails() {
    }

    public ProductDetails(int productId, String productName, double price, int stock, String categoryName, String vendorName) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.stock = stock;
        this.categoryName = categoryName;
        this.vendorName = vendorName;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

}
