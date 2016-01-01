package com.sms.configuration;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

public class CORSFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {


        String originHeader = request.getHeader("Origin");
        if (getAllowedOrigins().contains(request.getHeader("Origin"))) {
            response.addHeader("Access-Control-Allow-Origin", originHeader);
        }

        response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
        response.addHeader("Access-Control-Allow-Headers", "Content-Type");
        response.addHeader("Access-Control-Max-Age", "1800");
        filterChain.doFilter(request, response);

    }

    private  Set<String> getAllowedOrigins(){
        Properties prop = new Properties();
        InputStream in = getClass().getResourceAsStream("/app.properties");
        try {
            prop.load(in);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new HashSet<String>(Arrays.asList(prop.getProperty("allowed.origins").split(",")));
    }



}