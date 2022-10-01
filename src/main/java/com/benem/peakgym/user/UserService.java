package com.benem.peakgym.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserEntity signupUser(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    public UserEntity findUserById(String userId) {
        return userRepository.findById(userId).get();
    }
}
