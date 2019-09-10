package com.ekino.fth.sampleapp.configuration;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.elastic.apm.api.ElasticApm;
import org.springframework.boot.web.servlet.filter.OrderedFilter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import static java.util.Optional.ofNullable;

@Component
public class ApmContextFilter extends OncePerRequestFilter implements OrderedFilter {

    @Override
    public int getOrder() {
        return Integer.MAX_VALUE;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        ofNullable(SecurityContextHolder.getContext().getAuthentication())
                .map(Authentication::getName)
                .ifPresent(username -> ElasticApm.currentTransaction().setUser(null, null, username));

        filterChain.doFilter(request, response);
    }

}
