package com.ecom.exception;

import javax.management.relation.RoleInfoNotFoundException;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String message){
        super(message);
    }
}
