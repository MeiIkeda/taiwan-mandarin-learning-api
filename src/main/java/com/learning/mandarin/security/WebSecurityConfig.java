package com.learning.mandarin.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //.anyRequest().access("hasIpAddress('0:0:0:0:0:0:0:1') or hasIpAddress('127.0.0.1')")
                .antMatchers("/api/v1/**").permitAll()
                .and()
                .csrf().disable();
    }
}
