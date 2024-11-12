package com.modak.backend.filter;

import com.modak.backend.auth.JwtProcess;
import com.modak.backend.auth.PrincipalDetails;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
@RequiredArgsConstructor
public class TokenAuthenticationFilter extends OncePerRequestFilter {
  private final JwtProcess jwtProcess;
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain chain) throws IOException, ServletException {

    log.info("jwt AuthFilter");
    if(isHeaderVerify(request)) {
      String token = request.getHeader("Authorization").replace("Bearer ", "");
      log.info("token: {}", token);
      try {
        PrincipalDetails loginUser = jwtProcess.verifyToken(token);

        Authentication authentication = new UsernamePasswordAuthenticationToken(
            loginUser.getUsername(), loginUser.getPassword(), loginUser.getAuthorities()
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        log.info("context: {}",  SecurityContextHolder.getContext().toString());
        chain.doFilter(request, response);

      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    chain.doFilter(request, response);
  }
  private boolean isHeaderVerify(HttpServletRequest request) {
    String header = request.getHeader("Authorization");
    if (header == null || !header.startsWith("Bearer ")) {
      return false;
    } else {
      return true;
    }
  }
}
