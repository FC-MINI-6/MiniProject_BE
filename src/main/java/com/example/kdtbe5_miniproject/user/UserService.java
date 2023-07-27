package com.example.kdtbe5_miniproject.user;

import com.example.kdtbe5_miniproject._core.util.EncryptUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserResponse.JoinDTO joinUser(UserRequest.JoinDTO joinDTO) {
        joinDTO.setPassword(passwordEncoder.encode(joinDTO.getPassword()));

        //사용자 정보 암호화 - 기능 구현 시 헷갈릴 것 같아서 주석 처리
//        joinDTO.setUsername(EncryptUtils.encrypt(joinDTO.getUsername()));
//        joinDTO.setPhoneNumber(EncryptUtils.encrypt(joinDTO.getPhoneNumber()));
//        joinDTO.setEmail(EncryptUtils.encrypt(joinDTO.getEmail()));

        User userPS = userRepository.save(joinDTO.toEntity());
        return new UserResponse.JoinDTO(userPS);
    }
}
