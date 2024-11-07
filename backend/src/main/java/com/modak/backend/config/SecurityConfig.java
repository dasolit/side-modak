package com.modak.backend.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.modak.backend.auth.CustomOAuth2UserService;
import com.modak.backend.domain.Role;
import com.modak.backend.handler.OAuth2AuthenticationFailureHandler;
import com.modak.backend.handler.OAuth2AuthenticationSuccessHandler;
import com.modak.backend.repository.UserRepository;
import com.modak.backend.service.JwtService;
import com.modak.backend.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsUtils;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

  private final LoginService loginService;
  private final JwtService jwtService;
  private final UserRepository userRepository;
  private final ObjectMapper objectMapper;
  private final CustomOAuth2UserService customOAuth2UserService;
  private final OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;
  private final OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .csrf(AbstractHttpConfigurer::disable)
        .formLogin(AbstractHttpConfigurer::disable)
        .httpBasic(AbstractHttpConfigurer::disable)
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests((authorizeRequests) ->
            authorizeRequests
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                .requestMatchers("/", "/home","/user/join", "/WEB-INF/**", "/js/**", "/css/**", "/image/**", "/favicon.ico").permitAll()
                .requestMatchers("/api/v1/user/**").hasRole(Role.USER.name())
                .anyRequest().authenticated())
        .oauth2Login(oauth -> {
          oauth.userInfoEndpoint(userInfoEndpoint -> userInfoEndpoint.userService(customOAuth2UserService))
          .successHandler(oAuth2AuthenticationSuccessHandler)
          .failureHandler(oAuth2AuthenticationFailureHandler);
        });
    return http.build();
  }
  @Bean
  public BCryptPasswordEncoder encode(){
    return new BCryptPasswordEncoder();
  }
}
