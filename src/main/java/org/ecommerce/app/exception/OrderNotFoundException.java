package org.ecommerce.app.exception;

public class OrderNotFoundException extends RuntimeException {

    public OrderNotFoundException(Long id) {
        super("No order found with ID " + id);
    }
}
