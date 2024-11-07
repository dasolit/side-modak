package com.modak.backend.contoller;

import com.modak.backend.auth.PrincipalDetails;
import com.modak.backend.util.ApiResponse;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class HomeController {

  @GetMapping("/home")
  public ApiResponse<String> home(HttpServletResponse response,@AuthenticationPrincipal PrincipalDetails principalDetails) throws Exception {
    log.info(principalDetails.toString());
    response.setHeader("hghh","hfsd");
    return ApiResponse.ok(200, "테스트");
  }
}
