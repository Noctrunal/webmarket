package com.webmarket.application.controller.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ResponseStatus(value = INTERNAL_SERVER_ERROR, reason = "Fail to upload resource")
public class ResourceUploadException extends RuntimeException {
    public ResourceUploadException(String message) {
        super(message);
    }
}
