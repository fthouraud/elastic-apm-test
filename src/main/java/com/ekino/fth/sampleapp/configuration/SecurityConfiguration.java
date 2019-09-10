package com.ekino.fth.sampleapp.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().disable()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/foos/**").permitAll()
                .antMatchers("/management/**").permitAll()
                .antMatchers("/users/**").hasRole("BASIC_USER")
                .and()
                .httpBasic()
                .authenticationEntryPoint(new Http403ForbiddenEntryPoint());
    }

}
