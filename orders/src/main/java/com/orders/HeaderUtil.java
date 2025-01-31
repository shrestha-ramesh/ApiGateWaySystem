package com.orders;

public class HeaderUtil {
    private static final ThreadLocal<String> token = new ThreadLocal<>();
    public static void setToken(String tokenValue) {
        token.set(tokenValue);
    }
    public static String getToken() {
        return token.get();
    }
    public static void clear() {
        token.remove();
    }
}
