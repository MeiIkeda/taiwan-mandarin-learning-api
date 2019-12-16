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
                .anyRequest()
                //.antMatchers("/api/v1/**").permitAll()
                //.antMatchers("/api/v1/**")
                .access(
                        "hasIpAddress('0:0:0:0:0:0:0:1') or hasIpAddress('127.0.0.1')")
                //.anyRequest().authenticated()
                // .and()
                // .formLogin().permitAll()
                .and()
                .csrf().disable();
    }
}
