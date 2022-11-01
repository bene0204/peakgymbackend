package com.benem.peakgym.auth;

import com.benem.peakgym.auth.dto.LoginDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

  private final TokenService tokenService;

  private final AuthenticationManager authenticationManager;

  @PostMapping("api/login")
  public String login(@RequestBody LoginDTO userLogin) {
    Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLogin.getEmail(), userLogin.getPassword()));
    return tokenService.generateToken(authentication);
  }
}
