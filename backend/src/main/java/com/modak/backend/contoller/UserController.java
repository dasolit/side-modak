package com.modak.backend.contoller;


import com.modak.backend.dto.UserDTO;
import com.modak.backend.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

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
}
