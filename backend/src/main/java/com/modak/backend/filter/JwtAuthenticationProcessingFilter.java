package com.modak.backend.filter;

import com.modak.backend.domain.User;
import com.modak.backend.repository.UserRepository;
import com.modak.backend.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.NullAuthoritiesMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.filter.OncePerRequestFilter;

@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationProcessingFilter extends OncePerRequestFilter {

  private static final String NO_CHECK_URL = "/login";

  private final JwtService jwtService;
  private final UserRepository userRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  private GrantedAuthoritiesMapper authoritiesMapper = new NullAuthoritiesMapper();

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    if (request.getRequestURI().equals(NO_CHECK_URL)) {
      filterChain.doFilter(request, response);
      return;
    }

    String refreshToken = jwtService.extractRefreshToken(request)
        .filter(jwtService::isTokenValid)
        .orElse(null);

    if (refreshToken != null) {
      checkRefreshTokenAndReIssueAccessToken(response, refreshToken);
    } else{
      checkAccessTokenAndAuthentication(request, response, filterChain);
    }
  }

  public void checkRefreshTokenAndReIssueAccessToken(HttpServletResponse response, String refreshToken) {
    userRepository.findByRefreshToken(refreshToken)
        .ifPresent(user -> {
          String reIssuedRefreshToken = reIssueRefreshToken(user);
          jwtService.sendAccessAndRefreshToken(response, jwtService.createAccessToken(user.getEmail()),
              reIssuedRefreshToken);
        });
  }

  private String reIssueRefreshToken(User user) {
    String reIssuedRefreshToken = jwtService.createRefreshToken();
    user.updateRefreshToken(reIssuedRefreshToken);
    userRepository.saveAndFlush(user);
    return reIssuedRefreshToken;
  }

  public void checkAccessTokenAndAuthentication(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    log.info("checkAccessTokenAndAuthentication() 호출");
    jwtService.extractAccessToken(request)
        .filter(jwtService::isTokenValid).flatMap(jwtService::extractEmail)
        .flatMap(userRepository::findByEmail).ifPresent(this::saveAuthentication);

    filterChain.doFilter(request, response);
  }

  public void saveAuthentication(User myUser) {
    String password = myUser.getPassword();
    if (password == null) {
      password = bCryptPasswordEncoder.encode(UUID.randomUUID().toString());
    }

    UserDetails userDetailsUser = org.springframework.security.core.userdetails.User.builder()
        .username(myUser.getEmail())
        .password(password)
        .roles(myUser.getRole().name())
        .build();

    Authentication authentication =
        new UsernamePasswordAuthenticationToken(userDetailsUser, null,
            authoritiesMapper.mapAuthorities(userDetailsUser.getAuthorities()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
  }
}
