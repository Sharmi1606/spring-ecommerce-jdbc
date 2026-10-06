package com.ecom.service;

import com.ecom.dto.ProductDetails;
import com.ecom.dto.VendorProductCount;
import com.ecom.exception.InvalidInputException;
import com.ecom.exception.ProductNotFoundException;
import com.ecom.model.Product;
import com.ecom.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
 private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void saveProduct(Product product){
        if(product==null){
            throw new InvalidInputException("Product cannot be null");
        }
        // Product name validation
        if (product.getName() == null ||
                product.getName().trim().isEmpty()) {

            throw new InvalidInputException(
                    "Product name cannot be empty"
            );
        }

        // Price validation
        if (product.getPrice() <= 0) {

            throw new InvalidInputException(
                    "Product price must be greater than 0"
            );
        }

        // Stock validation
        if (product.getStockQuantity() < 0) {

            throw new InvalidInputException(
                    "Stock quantity cannot be negative"
            );
        }

        // Category ID validation
        if (product.getCategoryId() == null ||
                product.getCategoryId() <= 0) {

            throw new InvalidInputException(
                    "Category ID must be greater than 0"
            );
        }

        // Vendor ID validation
        if (product.getVendorId() == null ||
                product.getVendorId() <= 0) {

            throw new InvalidInputException(
                    "Vendor ID must be greater than 0"
            );
        }

        // Save product
        productRepository.saveProduct(product);
    }

    public ProductDetails findById(Long id) {

        if (id == null || id <= 0) {
            throw new InvalidInputException(
                    "Product ID must be greater than 0"
            );
        }

        return productRepository.findById(id);
    }
//Task 3
    public void updateStock(Long productId, int newQuantity) {

        if (productId == null || productId <= 0) {
            throw new InvalidInputException(
                    "Product ID must be greater than 0"
            );
        }

        if (newQuantity < 0) {
            throw new InvalidInputException(
                    "Stock quantity cannot be negative"
            );
        }

        int rowsUpdated = productRepository.updateStock(
                productId,
                newQuantity
        );

        if (rowsUpdated == 0) {
            throw new ProductNotFoundException(
                    "Product not found with ID: " + productId
            );
        }
    }
    //Task 4

    public List<VendorProductCount> countProductsByVendor() {

        return productRepository.countProductsByVendor();
    }

}
