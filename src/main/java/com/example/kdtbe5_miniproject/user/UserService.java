package com.example.kdtbe5_miniproject.user;

import com.example.kdtbe5_miniproject._core.errors.exception.UnCorrectPasswordException;
import com.example.kdtbe5_miniproject._core.errors.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.Optional;

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

    @Transactional
    public void updatePhoneNumber(User updateUser, UserRequest.UpdateDTO updateDTO) {
        Optional<User> userOP = userRepository.findByEmail(updateUser.getEmail());
        User user = userOP.get();
        user.updatePhoneNumber(updateDTO.getPhoneNumber());
    }

    @Transactional
    public void updatePwd(UserRequest.ModifyPwdDTO request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new UserNotFoundException("사용자를 찾을 수 없습니다."));

        if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
            throw new UnCorrectPasswordException("비밀번호가 일치하지 않습니다.");
        }
        userRepository.updateById(passwordEncoder.encode(request.getNewPassword()), request.getUserId());
    }
}
