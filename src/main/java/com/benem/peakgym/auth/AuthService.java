package com.benem.peakgym.auth;

import java.security.Principal;

import com.benem.peakgym.auth.dto.LoginResponse;
import com.benem.peakgym.auth.dto.LoginDTO;
import com.benem.peakgym.user.UserEntity;
import com.benem.peakgym.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService {

  private final TokenService tokenService;

  private final AuthenticationManager authenticationManager;

  private final UserService userService;


    public LoginResponse login(LoginDTO userLogin) {
      Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLogin.getEmail(), userLogin.getPassword()));
      String token = tokenService.generateToken(authentication);
      UserEntity user = userService.findByEmail(userLogin.getEmail());
      return new LoginResponse(user, token);
    }

}
