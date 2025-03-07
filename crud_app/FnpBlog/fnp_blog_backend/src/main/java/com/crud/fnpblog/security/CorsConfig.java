//package com.crud.fnpblog.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class CorsConfig {
//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        System.out.println("------------into corsconfigurer method of CorsConfig class  which returns allowed paths to localhost:4200----------");
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/api/**") // Allow all API paths
//                        .allowedOrigins("http://localhost:4200") // Allow frontend
//                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Allow methods
//                        .allowedHeaders("*") // Allow all headers
//                        .allowCredentials(true); // Allow cookies/sessions
//            }
//        };
//    }
//}
