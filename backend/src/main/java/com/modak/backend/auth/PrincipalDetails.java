package com.modak.backend.auth;

import com.modak.backend.domain.User;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class PrincipalDetails implements OAuth2User, UserDetails {

  private User user;

  private Map<String, Object> attributes;


  public PrincipalDetails(User user) {
    this.user = user;
  }

  public PrincipalDetails(User user, Map<String, Object> attributes) {
    this.user = user;
    this.attributes = attributes;
  }
  public User getUser(){
    return user;
  }

  @Override
  public Map<String, Object> getAttributes() {
    return attributes;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    Collection<GrantedAuthority> authorities = new ArrayList<>();
    authorities.add((GrantedAuthority) () -> {
      return "ROLE_"+user.getRole();
    });
    return authorities;
  }

  @Override
  public String getPassword() {
    return this.user.getPassword();
  }

  @Override
  public String getUsername() {
    return this.user.getEmail();
  }

  @Override
  public String getName() {
    return this.getUsername();
  }


}
