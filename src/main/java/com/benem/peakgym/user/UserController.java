package com.benem.peakgym.user;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import javax.mail.MessagingException;
import javax.validation.Valid;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("management/api/user/signup")
    public UserEntity signUpUser(@Valid @RequestBody UserEntity userEntity) throws MessagingException, GeneralSecurityException, IOException {
        return userService.signupUser(userEntity);
    }

    @PatchMapping("management/api/user/{userId}/update")
    public UserEntity updateUser(@Valid @RequestBody UserEntity userEntity,
                                 @PathVariable("userId") String userId) {
        return userService.updateUser(userEntity, userId);
    }

    @GetMapping("management/api/user/{userId}")
    public UserEntity getUserById(@PathVariable("userId") String userId){
        return userService.findUserById(userId);
    }

    @GetMapping("management/api/user/search")
    public List<UserEntity> findUsers(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        return userService.findUsers(firstName, lastName);
    }
}
