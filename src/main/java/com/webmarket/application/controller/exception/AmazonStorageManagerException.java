package com.webmarket.application.controller.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ResponseStatus(value = INTERNAL_SERVER_ERROR, reason = "Connection to AWS failed")
public class AmazonStorageManagerException extends RuntimeException {
    public AmazonStorageManagerException(String message) {
        super(message);
    }
}
