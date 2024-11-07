package com.modak.backend.filter;

import com.modak.backend.auth.JwtProcess;
import com.modak.backend.auth.PrincipalDetails;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

  private JwtProcess jwtProcess;
  public JwtAuthorizationFilter(AuthenticationManager authenticationManager, JwtProcess jwtProcess) {
    super(authenticationManager);
    this.jwtProcess = jwtProcess;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain chain) throws IOException, ServletException {
    if(isHeaderVerify(request)) {
      String token = request.getHeader("Authorization").replace("Bearer", "");
      try {
        PrincipalDetails loginUser = jwtProcess.verifyToken(token);

        Authentication authentication = new UsernamePasswordAuthenticationToken(
            loginUser.getUsername(), loginUser.getPassword(), loginUser.getAuthorities()
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    super.doFilterInternal(request, response, chain);
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
