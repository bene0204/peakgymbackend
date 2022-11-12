package com.benem.peakgym.auth;

import com.benem.peakgym.auth.dto.LoginResponse;
import com.benem.peakgym.auth.dto.LoginDTO;
import com.benem.peakgym.user.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
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

  @GetMapping("public/api/autologin")
  public UserEntity autoLogin() {
    return authService.autoLogin();
  }
}
