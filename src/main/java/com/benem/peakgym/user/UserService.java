package com.benem.peakgym.user;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    private final PasswordEncoder encoder;

    public UserEntity signupUser(UserEntity userEntity) {
        String hashedPassword = encoder.encode(userEntity.getPassword());
        userEntity.setPassword(hashedPassword);
        return userRepository.save(userEntity);
    }

    public UserEntity findUserById(String userId) {
        return userRepository.findById(userId).get();
    }

    public UserEntity findByEmail(String email) {
        return userRepository.findFirstByEmail(email).get();
    }

}
