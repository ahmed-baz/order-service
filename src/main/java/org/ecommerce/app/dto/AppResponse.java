package org.ecommerce.app.dto;

import org.springframework.http.HttpStatus;

import java.util.Date;

public record AppResponse<T>(
        Date responseDate,
        int statusCode,
        String message,
        T data
) {

    public AppResponse(T data) {
        this(new Date(), HttpStatus.OK.value(), null, data);
    }

    public AppResponse(HttpStatus status) {
        this(new Date(), status.value(), null, null);
    }

    public AppResponse(T data, HttpStatus status) {
        this(new Date(), status.value(), null, data);
    }

    public AppResponse(HttpStatus status, String message) {
        this(new Date(), status.value(), message, null);
    }

}
