package com.modak.backend.service;

import com.modak.backend.domain.Role;
import com.modak.backend.domain.SocialType;
import com.modak.backend.domain.User;
import com.modak.backend.dto.UserDTO;
import com.modak.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  public void signUp(UserDTO userDTO) throws Exception {

    if (userRepository.findByEmail(userDTO.getEmail()).isPresent()) {
      throw new Exception("이미 존재하는 이메일입니다.");
    }
    String encPassword =bCryptPasswordEncoder.encode(userDTO.getPassword());

    User user = User.builder()
        .email(userDTO.getEmail())
        .contact("")
        .name("")
        .refreshToken("")
        .password(encPassword)
        .socialType(SocialType.OUR)
        .role(Role.USER)
        .build();

    userRepository.save(user);
  }

  public void logout(User user) {

  }
}
