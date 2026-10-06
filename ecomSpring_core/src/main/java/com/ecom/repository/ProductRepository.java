package com.ecom.repository;

import com.ecom.dto.ProductDetails;
import com.ecom.dto.VendorProductCount;
import com.ecom.mapper.ProductMapper;
import com.ecom.mapper.VendorProductCountMapper;
import com.ecom.model.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepository {

    private final JdbcTemplate jdbcTemplate;

    public ProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //Task 1

    public void saveProduct(Product product){
        String sql= """
                INSERT INTO product
                (name,price,stock_quantity,category_id,vendor_id)
                VALUES (?,?,?,?,?)""";

        Object[] values= new Object[]{
                product.getName(),
                product.getPrice(),
                product.getStockQuantity(),
                product.getCategoryId(),
                product.getVendorId()
        };
        jdbcTemplate.update(sql,values);
    }

    //Task 2

    public ProductDetails findById(Long id) {

        String sql = """
            SELECT
                p.id,
                p.name,
                p.price,
                p.stock_quantity,
                c.name AS category_name,
                v.name AS vendor_name
            FROM product p
            JOIN category c ON p.category_id = c.id
            JOIN vendor v ON p.vendor_id = v.id
            WHERE p.id = ?
            """;

        Object[] values = new Object[]{id};

        return jdbcTemplate.queryForObject(
                sql,
                values,
                new ProductMapper()
        );
    }

    // Task 3

    public int updateStock(Long productId, int newQuantity) {

        String sql = """
            UPDATE product
            SET stock_quantity = ?
            WHERE id = ?
            """;

        Object[] values = new Object[]{
                newQuantity,
                productId
        };

        return jdbcTemplate.update(sql, values);
    }

// Task 4

    public List<VendorProductCount> countProductsByVendor() {

        String sql = """
            SELECT
                v.name AS vendor_name,
                COUNT(p.id) AS product_count
            FROM vendor v
            JOIN product p ON v.id = p.vendor_id
            GROUP BY v.id, v.name
            """;

        return jdbcTemplate.query(
                sql,
                new VendorProductCountMapper()
        );
    }
}
