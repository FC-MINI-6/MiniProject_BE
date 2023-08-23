package com.example.kdtbe5_miniproject._core.util;

import com.example.kdtbe5_miniproject.user.User;
import com.example.kdtbe5_miniproject.user.UserPosition;
import com.example.kdtbe5_miniproject.user.UserRepository;
import com.example.kdtbe5_miniproject.user.UserRoles;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;

@RequiredArgsConstructor
@Component
public class DBInit {

    private final PasswordEncoder passwordEncoder;

    @Profile("dev")
    @Bean
    CommandLineRunner initDB(UserRepository userRepository){
        return args -> {
            User user = User.builder()
                    .username("최수현")
                    .password(passwordEncoder.encode("0000"))
                    .email("csh@fastcampus.com")
                    .phoneNumber("01011112222")
                    .position(UserPosition.valueOf("사원"))
                    .roles(UserRoles.valueOf("일반"))
                    .joinDate(LocalDate.now())
                    .build();
            User admin = User.builder()
                    .username("한세희")
                    .password(passwordEncoder.encode("0000"))
                    .email("hsh@fastcampus.com")
                    .phoneNumber("01011112222")
                    .position(UserPosition.valueOf("부장"))
                    .roles(UserRoles.valueOf("관리자"))
                    .joinDate(LocalDate.now())
                    .build();
            userRepository.saveAll(Arrays.asList(user, admin));
        };
    }
}
