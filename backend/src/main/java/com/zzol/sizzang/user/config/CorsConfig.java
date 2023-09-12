package com.zzol.sizzang.user.config;

 import org.springframework.context.annotation.Configuration;
 import org.springframework.web.servlet.config.annotation.CorsRegistry;
 import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

 @Configuration
 public class CorsConfig implements WebMvcConfigurer {

     @Override
     public void addCorsMappings(CorsRegistry registry) {
         registry.addMapping("/**")
                 .allowedOrigins("http://13.124.27.170:8080",  "http://localhost:3000")
                 .allowedMethods("*")
                 .allowedHeaders("*")
                 .exposedHeaders("*")
                 .allowedOriginPatterns("*")
                 .allowCredentials(true);
     }
 }
