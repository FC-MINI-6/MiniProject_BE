package com.example.kdtbe5_miniproject._core.security;

import com.example.kdtbe5_miniproject.user.User;
import com.example.kdtbe5_miniproject.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    // login 호출
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("username : " + username);
        Optional<User> userOP = userRepository.findByUsername(username);

        if (userOP.isPresent()) {
            return new CustomUserDetails(userOP.get());
        } else {
            return null;
        }

    }
}
