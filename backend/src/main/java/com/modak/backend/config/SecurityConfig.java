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
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
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
  private final LoginSuccessHandler oAuth2LoginSuccessHandler;
  private final LoginFailureHandler oAuth2LoginFailureHandler;
  private final CustomOAuth2UserService customOAuth2UserService;
  private final BCryptPasswordEncoder passwordEncoder;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    provider.setPasswordEncoder(passwordEncoder);
    provider.setUserDetailsService(loginService);


    AuthenticationManagerBuilder auth = http.getSharedObject(AuthenticationManagerBuilder.class);
    auth.authenticationProvider(provider);

    CustomJsonUsernamePasswordAuthenticationFilter customJsonUsernamePasswordLoginFilter
        = new CustomJsonUsernamePasswordAuthenticationFilter(objectMapper);
    customJsonUsernamePasswordLoginFilter.setAuthenticationManager(auth.getObject());
    customJsonUsernamePasswordLoginFilter.setAuthenticationSuccessHandler(loginSuccessHandler());
    customJsonUsernamePasswordLoginFilter.setAuthenticationFailureHandler(loginFailureHandler());

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
          oauth.successHandler(oAuth2LoginSuccessHandler);
          oauth.failureHandler(oAuth2LoginFailureHandler);
          oauth.userInfoEndpoint(userInfoEndpoint -> userInfoEndpoint.userService(customOAuth2UserService));
        })
        .formLogin(login -> login.loginPage("/auth/loginForm")
            .loginProcessingUrl("/auth/loginProc")
            .defaultSuccessUrl("/")
        );
    http.addFilterAfter(customJsonUsernamePasswordLoginFilter, LogoutFilter.class);
    http.addFilterBefore(jwtAuthenticationProcessingFilter(), CustomJsonUsernamePasswordAuthenticationFilter.class);
    return http.build();
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
  public JwtAuthenticationProcessingFilter jwtAuthenticationProcessingFilter() {
    JwtAuthenticationProcessingFilter jwtAuthenticationFilter = new JwtAuthenticationProcessingFilter(jwtService, userRepository, passwordEncoder);
    return jwtAuthenticationFilter;
  }

  @Bean
  public BCryptPasswordEncoder encode(){
    return new BCryptPasswordEncoder();
  }

}
