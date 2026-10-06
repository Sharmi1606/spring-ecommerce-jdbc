package com.ecom.mapper;

import com.ecom.dto.VendorProductCount;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VendorProductCountMapper implements RowMapper<VendorProductCount> {
    @Override
    public VendorProductCount mapRow(
            ResultSet rs,
            int rowNum
    ) throws SQLException {

        VendorProductCount dto = new VendorProductCount();

        dto.setVendorName(
                rs.getString("vendor_name")
        );

        dto.setProductCount(
                rs.getInt("product_count")
        );

        return dto;
    }
}
