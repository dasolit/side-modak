package com.modak.backend.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SocialType {

  GOOGLE("GOOGLE"),
  NAVER("NAVER"),
  KAKAO("KAKAO"),
  OUR("OUR");


  private final String platform;
}
