package com.ecom.mapper;

import com.ecom.dto.ProductDetails;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductMapper implements RowMapper<ProductDetails> {

    public ProductDetails mapRow(
            ResultSet rs,
            int rowNum
    ) throws SQLException{
        ProductDetails dto=new ProductDetails();
        dto.setProductId(rs.getInt("id"));
        dto.setProductName(rs.getString("name"));
        dto.setPrice(rs.getDouble("price"));
        dto.setStock(rs.getInt("stock_quantity"));

        dto.setCategoryName(
                rs.getString("category_name"));

        dto.setVendorName(
                rs.getString("vendor_name"));

        return dto;

    }

}
