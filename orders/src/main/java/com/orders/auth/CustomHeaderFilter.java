package com.orders.auth;


import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
public class CustomHeaderFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

//        Enumeration<String> headerNames = httpRequest.getHeaderNames();
//        while (headerNames.hasMoreElements()) {
//            String headerName = headerNames.nextElement();
//            String headerValue = httpRequest.getHeader(headerName);
//            System.out.println(headerName + ": " + headerValue);
//        }
        System.out.println("This is httpRequest "+httpRequest.getHeader("authorization"));
        String authorization = httpRequest.getHeader("authorization").trim();
        String token = getTokenFromBearerString(authorization);
        HeaderUtil.setToken(token);

        try {
            chain.doFilter(request, response);
        } finally {
            HeaderUtil.clear();
        }
    }
    public static String getTokenFromBearerString(String authorization) {
        int spaceIndex = authorization.indexOf(" ");
        return spaceIndex != -1 ? authorization.substring(spaceIndex + 1) : "";
    }
}
