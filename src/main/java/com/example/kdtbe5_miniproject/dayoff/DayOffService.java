package com.example.kdtbe5_miniproject.dayoff;

import com.example.kdtbe5_miniproject._core.errors.exception.UserNotFoundException;
import com.example.kdtbe5_miniproject.user.User;
import com.example.kdtbe5_miniproject.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DayOffService {
    private final DayOffRepository dayOffRepository;
    private final UserRepository userRepository;

    // 연차 신청
    @Transactional
    public void registerDayOff(Long userId, DayOffRequest.RegisterDTO registerDTO) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("사용자를 찾을 수 없습니다."));
        DayOff dayOff = registerDTO.toEntity(user);
        dayOffRepository.save(dayOff);
    }

    @Transactional(readOnly = true)
    public DayOffResponse.MyDayOffDTO myDayOffInfo(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("사용자를 찾을 수 없습니다."));
        // TODO 휴가 로직을 수정해야함
        return new DayOffResponse.MyDayOffDTO(15, 0, 5);
    }

    // 내 연차 리스트
    @Transactional(readOnly = true)
    public DayOffResponse.AppliedDayOffDTO[] myAppliedDayOffs(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("사용자를 찾을 수 없습니다."));
        List<DayOff> dayOffs = dayOffRepository.findByUser(user);
        return dayOffs.stream().map(DayOffResponse.AppliedDayOffDTO::new).toArray(DayOffResponse.AppliedDayOffDTO[]::new);
    }

    // 연차 신청 취소
    @Transactional
    public void cancelDayOff(Long dayoffId, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("사용자를 찾을 수 없습니다."));
        DayOff dayOff = dayOffRepository.findById(dayoffId).orElseThrow(() -> new UserNotFoundException("해당 연차 신청을 찾을 수 없습니다."));
        dayOffRepository.delete(dayOff);
    }
}
