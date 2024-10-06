package com.modak.backend.dto.request;

import com.modak.backend.domain.Role;
import com.modak.backend.domain.SocialType;
import com.modak.backend.domain.User;
import java.util.Map;
import lombok.Builder;
import lombok.Getter;

@Getter
public class OAuthAttributes {

  private Map<String, Object> attributes;
  private String nameAttributeKey;
  private String name;
  private String email;
  private String picture;
  private SocialType socialType;

  @Builder
  public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name, String email, String picture, SocialType socialType) {
    this.attributes = attributes;
    this.nameAttributeKey = nameAttributeKey;
    this.name = name;
    this.email = email;
    this.picture = picture;
    this.socialType = socialType;
  }

  public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
    if (registrationId.equals("naver")) {
      return ofNaver("id", attributes);
    } else if(registrationId.equals("kakao")) {
      return ofKakao("id", attributes);
    } else {
      return ofGoogle(userNameAttributeName, attributes);
    }
  }

  private static OAuthAttributes ofKakao(String userNameAttributeName, Map<String, Object> attributes) {
    Map<String, Object> properties = (Map<String, Object>) attributes.get("properties");
    Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
    return OAuthAttributes.builder()
        .name((String) properties.get("nickname"))
        .email((String) kakaoAccount.get("email"))
        .picture((String) properties.get("profile_image"))
        .socialType(SocialType.KAKAO)
        .attributes(attributes)
        .nameAttributeKey(userNameAttributeName)
        .build();
  }
  private static OAuthAttributes ofNaver(String userNameAttributeName, Map<String, Object> attributes) {
    Map<String, Object> response = (Map<String, Object>) attributes.get("response");
    return OAuthAttributes.builder()
        .name((String) response.get("name"))
        .email((String) response.get("email"))
        .picture((String) response.get("profile_image"))
        .socialType(SocialType.NAVER)
        .attributes(response)
        .nameAttributeKey(userNameAttributeName)
        .build();
  }

  private static OAuthAttributes ofGoogle (String userNameAttributeName, Map<String, Object> attributes){
    return OAuthAttributes.builder()
        .name((String) attributes.get("name"))
        .email((String) attributes.get("email"))
        .picture((String) attributes.get("picture"))
        .socialType(SocialType.GOOGLE)
        .attributes(attributes)
        .nameAttributeKey(userNameAttributeName)
        .build();
  }

  public User toEntity(){
    return User.builder()
        .name(name)
        .email(email)
        .picture(picture)
        .role(Role.USER)
        .socialType(socialType)
        .build();
  }
}