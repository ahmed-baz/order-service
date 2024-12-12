package org.ecommerce.app.handler;


import org.ecommerce.app.dto.AppResponse;
import org.ecommerce.app.exception.OrderNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class OrderExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) {
        AppResponse<Void> appResponse = new AppResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        return new ResponseEntity<>(appResponse, HttpStatus.OK);
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<Object> handleOrderNotFoundException(OrderNotFoundException ex, WebRequest request) {
        AppResponse<Void> appResponse = new AppResponse<>(HttpStatus.BAD_REQUEST, ex.getMessage());
        return new ResponseEntity<>(appResponse, HttpStatus.OK);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        String defaultMessage = ex.getFieldError().getDefaultMessage();
        AppResponse<Void> appResponse = new AppResponse<>(HttpStatus.BAD_REQUEST, defaultMessage);
        return new ResponseEntity<>(appResponse, HttpStatus.OK);
    }

}
