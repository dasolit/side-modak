package com.modak.backend.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.modak.backend.domain.Role;
import com.modak.backend.filter.CustomJsonUsernamePasswordAuthenticationFilter;
import com.modak.backend.filter.JwtAuthenticationProcessingFilter;
import com.modak.backend.handler.LoginFailureHandler;
import com.modak.backend.handler.LoginSuccessHandler;
import com.modak.backend.repository.UserRepository;
import com.modak.backend.service.JwtService;
import com.modak.backend.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

  private final LoginService loginService;
  private final JwtService jwtService;
  private final UserRepository userRepository;
  private final ObjectMapper objectMapper;
  private final CustomOAuth2UserService customOAuth2UserService;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .csrf(AbstractHttpConfigurer::disable)
        .formLogin(AbstractHttpConfigurer::disable)
        .httpBasic(AbstractHttpConfigurer::disable)
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests((authorizeRequests) ->
            authorizeRequests
                .requestMatchers("/","/user/join", "/WEB-INF/**", "/js/**", "/css/**", "/image/**", "/favicon.ico").permitAll()
                .requestMatchers("/api/v1/user/**").hasRole(Role.USER.name())
                .anyRequest().authenticated())
        .oauth2Login(oauth -> {
          oauth.successHandler(loginSuccessHandler());
          oauth.failureHandler( loginFailureHandler());
          oauth.userInfoEndpoint(userInfoEndpoint -> userInfoEndpoint.userService(customOAuth2UserService));
        })
        .formLogin(login -> login.loginPage("/auth/loginForm")
            .loginProcessingUrl("/auth/loginProc")
            .defaultSuccessUrl("/")
        );
    http.addFilterAfter(customJsonUsernamePasswordAuthenticationFilter(), LogoutFilter.class);
    http.addFilterBefore(jwtAuthenticationProcessingFilter(), CustomJsonUsernamePasswordAuthenticationFilter.class);
    return http.build();
  }
  @Bean
  public BCryptPasswordEncoder encode(){
    return new BCryptPasswordEncoder();
  }

  @Bean
  public AuthenticationManager authenticationManager() {
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    provider.setPasswordEncoder(encode());
    provider.setUserDetailsService(loginService);
    return new ProviderManager(provider);
  }

  @Bean
  public LoginSuccessHandler loginSuccessHandler() {
    return new LoginSuccessHandler(jwtService, userRepository);
  }

  @Bean
  public LoginFailureHandler loginFailureHandler() {
    return new LoginFailureHandler();
  }

  @Bean
  public CustomJsonUsernamePasswordAuthenticationFilter customJsonUsernamePasswordAuthenticationFilter() {
    CustomJsonUsernamePasswordAuthenticationFilter customJsonUsernamePasswordLoginFilter
        = new CustomJsonUsernamePasswordAuthenticationFilter(objectMapper);
    customJsonUsernamePasswordLoginFilter.setAuthenticationManager(authenticationManager());
    customJsonUsernamePasswordLoginFilter.setAuthenticationSuccessHandler(loginSuccessHandler());
    customJsonUsernamePasswordLoginFilter.setAuthenticationFailureHandler(loginFailureHandler());
    return customJsonUsernamePasswordLoginFilter;
  }

  @Bean
  public JwtAuthenticationProcessingFilter jwtAuthenticationProcessingFilter() {
    JwtAuthenticationProcessingFilter jwtAuthenticationFilter = new JwtAuthenticationProcessingFilter(jwtService, userRepository, encode());
    return jwtAuthenticationFilter;
  }
}
