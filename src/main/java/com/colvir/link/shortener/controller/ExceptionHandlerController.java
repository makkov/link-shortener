package com.colvir.link.shortener.controller;

import com.colvir.link.shortener.dto.ErrorResponse;
import com.colvir.link.shortener.exception.LinkNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.colvir.link.shortener.model.InternalErrorStatus.LINK_DOES_NOT_EXIST;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(LinkNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleLinkNotFoundException(Exception e) {
        ErrorResponse errorResponse = new ErrorResponse(LINK_DOES_NOT_EXIST, e.getMessage());
        return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
