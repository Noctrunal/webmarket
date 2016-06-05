package com.webmarket.application.controller.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.UNSUPPORTED_MEDIA_TYPE;

@ResponseStatus(value = UNSUPPORTED_MEDIA_TYPE, reason = "Invalid image format")
public class ImageUploadException extends RuntimeException {
    public ImageUploadException(String message) {
        super(message);
    }
}
