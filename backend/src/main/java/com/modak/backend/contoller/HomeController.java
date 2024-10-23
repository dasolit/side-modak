package com.modak.backend.contoller;

import com.modak.backend.util.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

  @GetMapping("/home")
  public ApiResponse<String> home() throws Exception {
    throw new Exception();
    //return ApiResponse.ok(200, "테스트");
  }
}
