package com.ecom.main;

import com.ecom.config.AppConfig;
import com.ecom.dto.ProductDetails;
import com.ecom.dto.VendorProductCount;
import com.ecom.exception.InvalidInputException;
import com.ecom.exception.ProductNotFoundException;
import com.ecom.model.Product;
import com.ecom.service.ProductService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        ApplicationContext context=new AnnotationConfigApplicationContext(AppConfig.class);
        ProductService productService=context.getBean(ProductService.class);

        Scanner scanner=new Scanner(System.in);

        while(true){
            System.out.println("\n===== E-COMMERCE MENU =====");
            System.out.println("1. Insert Product");
            System.out.println("2. Find Product By ID");
            System.out.println("3. Update Stock");
            System.out.println("4. Count Products By Vendor");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice){

                case 1:
                    // Insert Product
                    try {
                        System.out.print("Enter product name: ");
                        String name = scanner.next();

                        System.out.print("Enter price: ");
                        double price = scanner.nextDouble();

                        System.out.print("Enter stock quantity: ");
                        int stockQuantity = scanner.nextInt();

                        System.out.print("Enter category ID: ");
                        Long categoryId = scanner.nextLong();

                        System.out.print("Enter vendor ID: ");
                        Long vendorId = scanner.nextLong();

                        Product product = new Product();

                        product.setName(name);
                        product.setPrice(price);
                        product.setStockQuantity(stockQuantity);
                        product.setCategoryId(categoryId);
                        product.setVendorId(vendorId);

                        productService.saveProduct(product);

                        System.out.println("Product inserted successfully.");

                    }catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter the correct type.");
                        scanner.nextLine();

                    } catch (InvalidInputException e) {
                        System.out.println("Invalid input: " + e.getMessage());
                    }

                    break;

                case 2:
                    // Find Product

                    try {
                        System.out.print("Enter product ID: ");
                        Long productId = scanner.nextLong();

                        ProductDetails productDetails =
                                productService.findById(productId);

                        System.out.println("\n===== PRODUCT DETAILS =====");
                        System.out.println("Product ID: " +
                                productDetails.getProductId());
                        System.out.println("Product Name: " +
                                productDetails.getProductName());
                        System.out.println("Price: " +
                                productDetails.getPrice());
                        System.out.println("Stock: " +
                                productDetails.getStock());
                        System.out.println("Category: " +
                                productDetails.getCategoryName());
                        System.out.println("Vendor: " +
                                productDetails.getVendorName());

                    } catch (InvalidInputException e) {
                        System.out.println("Invalid input: " + e.getMessage());

                    } catch (ProductNotFoundException e) {
                        System.out.println("Product not found: " + e.getMessage());
                    }
                    break;

                case 3:
                    // Update Stock

                    try {
                        System.out.print("Enter product ID: ");
                        Long productId = scanner.nextLong();

                        System.out.print("Enter new stock quantity: ");
                        int newQuantity = scanner.nextInt();

                        productService.updateStock(productId, newQuantity);

                        System.out.println("Stock updated successfully.");

                    } catch (InvalidInputException e) {
                        System.out.println("Invalid input: " + e.getMessage());

                    } catch (ProductNotFoundException e) {
                        System.out.println("Product not found: " + e.getMessage());
                    }
                    break;

                case 4:
                    // Count Products By Vendor
                    List<VendorProductCount> vendorProductCounts =
                            productService.countProductsByVendor();

                    System.out.println("\n===== PRODUCT COUNT BY VENDOR =====");

                    for (VendorProductCount vendorProductCount : vendorProductCounts) {

                        System.out.println(
                                vendorProductCount.getVendorName()
                                        + " -> "
                                        + vendorProductCount.getProductCount()
                        );
                    }
                    break;

                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}
