package com.products.auth;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
public class CustomHeaderFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String authorization = httpServletRequest.getHeader("authorization").trim();
        String token = getTokenFromBearerString(authorization);

        HeaderUtil.setToken(token);
        try {
            chain.doFilter(request, response);
        }finally {
            HeaderUtil.clear();
        }
    }

    private String getTokenFromBearerString(String authorization) {
        int spaceIndex = authorization.indexOf(" ");
        return spaceIndex != -1 ? authorization.substring(spaceIndex+1) : "";
    }
}
