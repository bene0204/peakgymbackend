package com.benem.peakgym.service;

import java.util.Random;

public class PasswordService {

  public static String generatePassword(int length) {
    String capital = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String lower = "abcdefghijklmnopqrstuvwxyz";
    String special = "!@#$";
    String numbers = "1234567890";
    String characterPool = capital + lower + special + numbers;
    String password = "";

    Random random = new Random();

    for (int i = 0; i < length; i++) {
      password += characterPool.charAt(random.nextInt(characterPool.length()));
    }

    return password;
  }
}
