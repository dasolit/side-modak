package com.modak.backend.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SocialType {

  GOOGLE("GOOGLE"),
  NAVER("NAVER"),
  KAKAO("KAKAO");

  private final String platform;
}
