package com.benem.peakgym.user;

import javax.validation.Valid;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("management/api/user/signup")
    public UserEntity signUpUser(@Valid @RequestBody UserEntity userEntity) {
        return userService.signupUser(userEntity);
    }
}
