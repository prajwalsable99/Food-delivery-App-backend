package com.prajwal.FoodApp.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableWebSecurity
public class AppConfig {


    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        http.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.authorizeHttpRequests(
                authorize->authorize.requestMatchers("/api/admin/**")
                        .hasAnyRole("RESTAURANT_OWNER","ADMIN")
                        .requestMatchers("/api/**").authenticated()
                        .anyRequest().permitAll() //for sign_up,sign_in
        );

        http.addFilterBefore( new JwtTokenValidator() , BasicAuthenticationFilter.class);

        http.csrf(AbstractHttpConfigurer::disable);

        http.cors(cors->cors.configurationSource(  corsConfigurationSource()));



        return http.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {


       return new CorsConfigurationSource() {
           @Override
           public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {

               CorsConfiguration cfg=new CorsConfiguration();
               cfg.setAllowedOrigins(Collections.singletonList("*"));
               cfg.setAllowedOrigins(Arrays.asList(
                       "http://localhost:3000",
                       "http://localhost:3001"));
               //cfg.setAllowedMethods(Arrays.asList("GET", "POST","DELETE","PUT"));
               cfg.setAllowedMethods(Collections.singletonList("*"));
               cfg.setAllowCredentials(true);
               cfg.setAllowedHeaders(Collections.singletonList("*"));
               cfg.setExposedHeaders(List.of("Authorization"));
               cfg.setMaxAge(3600L);
               return  cfg;
           }
       };

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }


}

