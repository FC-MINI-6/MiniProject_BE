package com.example.kdtbe5_miniproject.duty;

import com.example.kdtbe5_miniproject._core.errors.exception.DutyNotFoundException;
import com.example.kdtbe5_miniproject._core.errors.exception.UserNotFoundException;
import com.example.kdtbe5_miniproject.dayoff.DayOff;
import com.example.kdtbe5_miniproject.user.User;
import com.example.kdtbe5_miniproject.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class DutyService {

    private final DutyRepository dutyRepository;
    private final UserRepository userRepository;

    // 당직 신청
    @Transactional
    public DutyResponse.DutyDTO createDuty(DutyRequest.DutyDTO dutyDTO, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("사용자를 찾을 수 없습니다."));

        // 지난 날짜에 당직을 신청하는지 확인
        LocalDate requestedDate = dutyDTO.getDate();
        if (requestedDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("과거의 날짜에 당직을 신청할 수 없습니다.");
        }
        
        // 당직을 같은날에 신청하는지 확인
        List<Duty> existingDuties = dutyRepository.findAllByUserAndDate(user, requestedDate);
        if (!existingDuties.isEmpty()) {
            throw new IllegalArgumentException("당직은 같은 날에 두 번 신청할 수 없습니다.");
        }

        Duty duty = dutyDTO.toEntity(user);
        Duty createdDuty = dutyRepository.save(duty);
        return new DutyResponse.DutyDTO(createdDuty);
    }


    // 내 당직 리스트
    @Transactional(readOnly = true)
    public List<DutyResponse.DutyDTO> getAppliedDuties(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("사용자를 찾을 수 없습니다."));
        return dutyRepository.findAllByUser(user).stream()
                .map(DutyResponse.DutyDTO::new)
                .collect(Collectors.toList());
    }

    // 당직 신청 취소
    @Transactional
    public void deleteDuty(Long dutyId, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("사용자를 찾을 수 없습니다."));
        Duty duty = dutyRepository.findByIdAndUser(dutyId, user).orElseThrow(() -> new DutyNotFoundException("해당 당직 신청을 찾을 수 없습니다."));

        // 본인 당직만 삭제 가능
        if (!duty.getUser().getId().equals(userId)) {
            throw new IllegalArgumentException("당신의 당직만 삭제할 수 있습니다.");
        }

        dutyRepository.delete(duty);
    }
}