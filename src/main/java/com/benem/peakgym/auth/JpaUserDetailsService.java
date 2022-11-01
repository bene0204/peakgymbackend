package com.benem.peakgym.auth;

import com.benem.peakgym.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JpaUserDetailsService implements UserDetailsService {

  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return userRepository
             .findFirstByEmail(username)
             .map(SecurityUserDetails::new)
             .orElseThrow(() -> new UsernameNotFoundException("Email not found: " + username));
  }
}
