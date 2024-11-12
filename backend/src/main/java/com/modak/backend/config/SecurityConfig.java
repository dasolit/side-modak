package com.modak.backend.config;

import com.modak.backend.auth.CustomOAuth2UserService;
import com.modak.backend.auth.JwtProcess;
import com.modak.backend.domain.Role;
import com.modak.backend.filter.TokenAuthenticationFilter;
import com.modak.backend.handler.OAuth2AuthenticationFailureHandler;
import com.modak.backend.handler.OAuth2AuthenticationSuccessHandler;
import com.modak.backend.handler.SecurityLogoutHandler;
import com.modak.backend.handler.SecurityLogoutSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsUtils;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

  private final CustomOAuth2UserService customOAuth2UserService;
  private final OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;
  private final OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler;
  private final SecurityLogoutHandler securityLogoutHandler;
  private final SecurityLogoutSuccessHandler securityLogoutSuccessHandler;
  private final JwtProcess jwtProcess;
  private final CorsConfig corsConfig;

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
      throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationManager authenticationManager) throws Exception {

    http
        .csrf(AbstractHttpConfigurer::disable)
        .formLogin(AbstractHttpConfigurer::disable)
        .httpBasic(AbstractHttpConfigurer::disable)
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests((authorizeRequests) ->
            authorizeRequests
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                .requestMatchers("/", "/home","/user/join", "/WEB-INF/**", "/js/**", "/css/**", "/image/**", "/favicon.ico","/user/logout").permitAll()
                .requestMatchers("/api/v1/user/**").hasRole(Role.USER.name())
                .anyRequest().authenticated())
        .oauth2Login(oauth -> {
          oauth.userInfoEndpoint(userInfoEndpoint -> userInfoEndpoint.userService(customOAuth2UserService))
          .successHandler(oAuth2AuthenticationSuccessHandler)
          .failureHandler(oAuth2AuthenticationFailureHandler);
        })
        .addFilter(corsConfig.corsFilter())
        .addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    return http.build();
  }
  @Bean
  public BCryptPasswordEncoder encode(){
    return new BCryptPasswordEncoder();
  }
  @Bean
  public TokenAuthenticationFilter tokenAuthenticationFilter() {
    return new TokenAuthenticationFilter(jwtProcess);
  }

}
