package com.modak.backend.handler;

import com.modak.backend.auth.JwtProcess;
import com.modak.backend.auth.PrincipalDetails;
import com.modak.backend.domain.User;
import com.modak.backend.service.RedisService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

  private final JwtProcess jwtProcess;
  private final RedisService redisService;

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
      Authentication auth) throws IOException, ServletException {
    PrincipalDetails principal = (PrincipalDetails) auth.getPrincipal();

    User user = principal.getUser();
    String additionalInputUri = "";
    String accessToken = jwtProcess.createAccessToken(principal);
    String refreshToken = saveRefreshToken(user);

    if(user.getName() == null) {
      additionalInputUri = "login";
    }
    log.info("accessToken={}", accessToken);
    log.info("refreshToken={}", refreshToken);
    addCookie(response,"accessToken", accessToken);
    addCookie(response, "refreshToken", refreshToken);
    redisService.set(user.getEmail(), refreshToken);
    log.info("redis={}", redisService.get(user.getEmail()));

    getRedirectStrategy().sendRedirect(request, response, "http://localhost:3000");

  }
  private static void addCookie(HttpServletResponse response, String name, String value, boolean httpOnly) {
    value = URLEncoder.encode(value);
    Cookie cookie = new Cookie(name, value);
    cookie.setHttpOnly(httpOnly);
    cookie.setPath("/");
    response.addCookie(cookie);
  }
  private static void addCookie(HttpServletResponse response, String name, String value) {
    addCookie(response, name, value, true);
  }
  private String saveRefreshToken(User user) {
    String email = user.getEmail();
    String role = user.getRoleKey();
    return jwtProcess.createRefreshToken(email, role);
  }

}
