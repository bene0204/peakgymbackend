package com.benem.peakgym.auth;

import com.benem.peakgym.auth.dto.LoginResponse;
import com.benem.peakgym.auth.dto.LoginDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

  private final AuthService authService;

  @PostMapping("public/api/login")
  public LoginResponse login(@RequestBody LoginDTO userLogin) {
    return authService.login(userLogin);
  }
}
