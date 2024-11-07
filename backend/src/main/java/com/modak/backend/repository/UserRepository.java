package com.modak.backend.repository;

import com.modak.backend.domain.SocialType;
import com.modak.backend.domain.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByEmail(String email);

  Optional<User> findByRefreshToken(String refreshToken);
  Optional<User> findBySocialType(SocialType socialType);

}
