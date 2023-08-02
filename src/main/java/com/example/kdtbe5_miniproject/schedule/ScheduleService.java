package com.example.kdtbe5_miniproject.schedule;

import com.example.kdtbe5_miniproject.user.UserRepository;
import com.example.kdtbe5_miniproject.user.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ScheduleService {

    private final UserRepository userRepository;

    // 전체 사용자 목록 조회
    @Transactional
    public List<UserResponse.AllUsersDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(UserResponse.AllUsersDTO::new)
                .collect(Collectors.toList());
    }

}
