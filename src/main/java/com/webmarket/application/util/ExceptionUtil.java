package com.webmarket.application.util;

import com.webmarket.application.controller.exception.NotFoundException;

public class ExceptionUtil {
    private ExceptionUtil() {
    }

    public static<T> T checkEntity(T entity, String id) {
        if (entity == null || entity.equals(0L)) {
            throw new NotFoundException("Not found entity with id=" + id);
        }
        return entity;
    }
}
