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

@Getter
@NoArgsConstructor
@Entity(name = "account")
public class User extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 50, nullable = false, unique = true)
  private String email;

  @Column(length = 50, nullable = false, unique = true)
  private String contact;

  @Column(length = 50, nullable = false, unique = true)
  private String name;

  @Column(nullable = true, unique = true)
  private String picture;

  @Enumerated(EnumType.STRING)
  private SocialType socialType;

  @Enumerated(EnumType.STRING)
  private Role role;

  @Builder
  public User(String email, String contact, String name, String picture, SocialType socialType, Role role) {
    this.role = role;
    this.email = email;
    this.contact = contact;
    this.name = name;
    this.picture = picture;
    this.socialType = socialType;
  }
}