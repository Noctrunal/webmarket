package com.webmarket.application.util;

class FilterUtil {
    private FilterUtil() {
    }

    static boolean isBetweenPrices(double start, double end, double value) {
        return Double.valueOf(value).compareTo(start) >= 0.0 && Double.valueOf(value).compareTo(end) <= 0.0;
    }

    static boolean isBetweenYears(int start, int end, int value) {
        return Integer.valueOf(value).compareTo(start) >= 0 && Integer.valueOf(value).compareTo(end) <= 0;
    }
}
