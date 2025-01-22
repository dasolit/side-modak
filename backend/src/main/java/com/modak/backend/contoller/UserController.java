package com.modak.backend.contoller;


import com.modak.backend.dto.UserDTO;
import com.modak.backend.service.RedisService;
import com.modak.backend.service.UserService;
import com.modak.backend.util.ApiResponse;
import com.modak.backend.util.HeaderUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

// pr 테스트
// pr 덮어쓰기
@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;
  private final RedisService redisService;

  @GetMapping
  public String getUser(HttpServletResponse response) {
    response.setHeader("hghh","hfsd");
    Map<String, String> map = new HashMap<>();
    map.put("username", "admin");

    return "하하하";
  }

  @PostMapping("")
  public String login(@RequestBody UserDTO user) {
    log.info(user.toString());
    return "로그인 성공";
  }

  @PostMapping("/user/join")
  public String join(@RequestBody UserDTO user) throws Exception {
    userService.signUp(user);
    log.info(user.toString());
    return "회원가입 성공";
  }

  @GetMapping("/user/logout")
  public ApiResponse<String> logout(HttpServletRequest request) throws Exception {
    log.info("logout");
    String token = HeaderUtil.getAccessToken(request);
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    Object principal = authentication.getPrincipal();

    redisService.delete(principal.toString());
    return ApiResponse.ok(200, "테스트");
  }
}
