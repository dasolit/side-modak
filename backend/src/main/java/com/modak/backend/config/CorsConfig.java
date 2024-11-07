package com.modak.backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")
        .allowedHeaders("Access-Control-Allow-Origin")
        .allowedOrigins("http://localhost:3000/")
        .allowedMethods("OPTIONS","GET","POST","PUT","DELETE")
        .exposedHeaders("X-AUTH-TOKEN");
  }
}
