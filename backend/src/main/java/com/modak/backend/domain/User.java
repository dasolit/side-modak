package com.modak.backend.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@NoArgsConstructor
@Entity(name = "account")
public class User extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 50, nullable = false, unique = true)
  private String email;

  @Column(nullable = true)
  private String password;

  @Column(length = 50, nullable = true, unique = true)
  private String contact;

  @Column(length = 50, nullable = false, unique = true)
  private String name;

  @Column(nullable = true, unique = true)
  private String picture;

  @Enumerated(EnumType.STRING)
  private SocialType socialType;

  @Enumerated(EnumType.STRING)
  private Role role;

  @Column(nullable = true)
  private String refreshToken;

  @Builder
  public User(String email, String password, String contact, String name, String picture, SocialType socialType, Role role, String refreshToken) {
    this.role = role;
    this.password = password;
    this.email = email;
    this.contact = contact;
    this.name = name;
    this.picture = picture;
    this.socialType = socialType;
    this.refreshToken = refreshToken;
  }

  public void passwordEncode(PasswordEncoder passwordEncoder) {
    this.password = passwordEncoder.encode(this.password);
  }

  public void updateRefreshToken(String updateRefreshToken) {
    this.refreshToken = updateRefreshToken;
  }

  public String getRoleKey() {
    return this.role.getKey();
  }
}