package com.payment;

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
        String token = getTokenFromBrearerString(authorization);

        HeaderUtil.setToken(token);

        try{
            chain.doFilter(request, response);
        }finally {
            HeaderUtil.clear();
        }
    }

    private String getTokenFromBrearerString(String authorization) {
        int spaceInder = authorization.indexOf(" ");
        return spaceInder != -1 ? authorization.substring(spaceInder + 1) : "";
    }
}
