package com.example.kdtbe5_miniproject.duty;

import com.example.kdtbe5_miniproject._core.errors.exception.DutyNotFoundException;
import com.example.kdtbe5_miniproject.user.User;
import com.example.kdtbe5_miniproject.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class DutyService {

    private final DutyRepository dutyRepository;
    private final UserRepository userRepository;

    @Transactional
    public DutyResponse.DutyDTO createDuty(DutyRequest.DutyDTO dutyDTO, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("잘못된 사용자 ID입니다."));
        Duty duty = Duty.builder()
                .date(dutyDTO.getDate())
                .reason(dutyDTO.getReason())
                .status("대기")
                .user(user)
                .build();
        Duty createdDuty = dutyRepository.save(duty);
        return new DutyResponse.DutyDTO(createdDuty);
    }

    @Transactional(readOnly = true)
    public List<DutyResponse.DutyDTO> getAppliedDuties(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("잘못된 사용자 ID입니다."));
        return dutyRepository.findAllByUser(user).stream()
                .map(DutyResponse.DutyDTO::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteDuty(Long dutyId, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("잘못된 사용자 ID입니다."));
        Duty duty = dutyRepository.findByIdAndUser(dutyId, user).orElseThrow(() -> new DutyNotFoundException("당직을 찾을 수 없습니다."));
        dutyRepository.delete(duty);
    }
}