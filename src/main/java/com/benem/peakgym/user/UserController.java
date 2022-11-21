package com.benem.peakgym.user;

import java.util.List;

import javax.validation.Valid;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
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
    public UserEntity signUpUser(@Valid @RequestBody UserEntity userEntity) {
        return userService.signupUser(userEntity);
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
