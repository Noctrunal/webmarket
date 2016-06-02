package com.webmarket.application.controller.exception;

class HttpExceptionInfo {
    private final String url;
    private final String cause;
    private final String detail;

    HttpExceptionInfo(CharSequence url, Throwable ex) {
        this.url = url.toString();
        this.cause = ex.getClass().getSimpleName();
        this.detail = ex.getLocalizedMessage();
    }

    public String getUrl() {
        return url;
    }

    public String getCause() {
        return cause;
    }

    public String getDetail() {
        return detail;
    }
}
