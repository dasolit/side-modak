package com.modak.backend.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.modak.backend.domain.Role;
import com.modak.backend.domain.User;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtProcess {

  private static final String BEARER = "Bearer ";
  private static final String ACCESS_TOKEN = "Authorization";
  private static final String REFRESH_TOKEN = "Refresh-Token";

  @Value("${jwt.secretKey}")
  private String secretKey;

  @Value("${jwt.access.expiration}")
  private int accessExpiration;

  @Value("${jwt.refresh.expiration}")
  private int refreshExpiration;

  public String createAccessToken(PrincipalDetails principal) {
    User user = principal.getUser();
    return createNewAccessToken(user.getEmail(), user.getRoleKey());
  }

  private String createNewAccessToken(String email, String role) {
    return JWT.create()
        .withExpiresAt(new Date(System.currentTimeMillis() + accessExpiration))
        .withClaim("email", email)
        .withClaim("role", role)
        .sign(Algorithm.HMAC512(secretKey));
  }

  public String createRefreshToken(String email, String role) {
    return JWT.create()
        .withExpiresAt(new Date(System.currentTimeMillis() + refreshExpiration))
        .withClaim("email", email)
        .withClaim("role", role)
        .sign(Algorithm.HMAC512(secretKey));
  }

  public PrincipalDetails verifyToken(String token) {
    DecodedJWT jwt = isValidToken(token);
    String email = jwt.getClaim("email").asString();
    User user = User.builder().email(email).role(Role.USER).build();
    return new PrincipalDetails(user);
  }

  private DecodedJWT isValidToken(String token) {
    return JWT.require(Algorithm.HMAC512(secretKey)).build().verify(token);
  }

}
