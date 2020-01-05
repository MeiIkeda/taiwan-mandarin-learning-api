package com.learning.mandarin.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override protected void configure(AuthenticationManagerBuilder auth) throws Exception { super.configure(auth); }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.httpBasic() //TODO temporal
                .and() //TODO temporal
                .authorizeRequests()
                .anyRequest().authenticated() //TODO temporal
                //.anyRequest().permitAll()//temporal solution
//                //IP固定出来たら
//                //.anyRequest().access("hasIpAddress('0:0:0:0:0:0:0:1') or hasIpAddress('127.0.0.1')")
                .and()
                .cors().configurationSource(corsConfigurationSource())
                .and()
                .csrf().disable();
    }

    private UrlBasedCorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedMethod(CorsConfiguration.ALL);
        corsConfiguration.addAllowedHeader(CorsConfiguration.ALL);
        //corsConfiguration.addAllowedOrigin("http://localhost:3000");//追加？
        corsConfiguration.addAllowedOrigin(CorsConfiguration.ALL);
        corsConfiguration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource corsConfigurationSource= new UrlBasedCorsConfigurationSource();
        corsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);

        return corsConfigurationSource;
    }
}
