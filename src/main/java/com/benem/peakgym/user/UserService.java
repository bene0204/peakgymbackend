package com.benem.peakgym.user;

import java.util.List;
import java.util.Objects;

import lombok.RequiredArgsConstructor;
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

    public List<UserEntity> findUsers(String firstName, String lastName) {
        return userRepository.findAllByFirstNameContainsIgnoreCaseAndLastNameContainsIgnoreCase(firstName, lastName);
  }

    public UserEntity updateUser(UserEntity updateUser, String userId) {
        UserEntity savedUser = findUserById(userId);

        if (!Objects.equals(updateUser.getFirstName(), savedUser.getFirstName())) {
            savedUser.setFirstName(updateUser.getFirstName());
        }
        if(!Objects.equals(updateUser.getLastName(), savedUser.getLastName())) {
            savedUser.setLastName(updateUser.getLastName());
        }
        if(updateUser.getBirthDate() != savedUser.getBirthDate()) {
            savedUser.setBirthDate(updateUser.getBirthDate());
        }
        if(!Objects.equals(updateUser.getEmail(), savedUser.getEmail())) {
            savedUser.setEmail(updateUser.getEmail());
        }
        if(!Objects.equals(updateUser.getPhoneNumber(), savedUser.getPhoneNumber())) {
            savedUser.setPhoneNumber(updateUser.getPhoneNumber());
        }
        if(updateUser.getBalance() != savedUser.getBalance()) {
            savedUser.setBalance(updateUser.getBalance());
        }
        if(updateUser.getRole() != savedUser.getRole()) {
            savedUser.setRole(updateUser.getRole());
        }
        return userRepository.save(savedUser);
    }
}
