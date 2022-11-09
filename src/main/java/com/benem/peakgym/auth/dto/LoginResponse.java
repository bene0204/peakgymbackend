package com.benem.peakgym.auth.dto;

import com.benem.peakgym.user.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LoginResponse {
  private UserEntity user;
  private String token;
}
