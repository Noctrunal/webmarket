package com.webmarket.application.controller.exception;

import org.springframework.core.annotation.Order;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ValidationException;

import static org.springframework.http.HttpStatus.*;

@ControllerAdvice(annotations = RestController.class)
public class HttpExceptionInfoHandler {
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    @ResponseBody
    @Order(1)
    public HttpExceptionInfo handleException(HttpServletRequest req, Exception e) {
        return getHttpExceptionInfo(req, e);
    }


    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    @ResponseBody
    @Order(2)
    public HttpExceptionInfo handleNotFoundException(HttpServletRequest req, NotFoundException e) {
        return getHttpExceptionInfo(req, e);
    }

    @ResponseStatus(UNPROCESSABLE_ENTITY)
    @ExceptionHandler(BindException.class)
    @ResponseBody
    @Order(3)
    public HttpExceptionInfo handleBindException(HttpServletRequest req, BindingResult result) {
        StringBuilder sb = new StringBuilder();
        result.getFieldErrors().forEach(fe -> sb.append(fe.getField()).append(" ").append(fe.getDefaultMessage()).append("<br>"));
        return getHttpExceptionInfo(req, new ValidationException(sb.toString()));
    }

    private HttpExceptionInfo getHttpExceptionInfo(HttpServletRequest req, Throwable e) {
        return new HttpExceptionInfo(req.getRequestURL(), e);
    }
}
