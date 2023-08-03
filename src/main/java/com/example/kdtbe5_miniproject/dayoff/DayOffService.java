package com.example.kdtbe5_miniproject.dayoff;

import com.example.kdtbe5_miniproject._core.errors.exception.UserNotFoundException;
import com.example.kdtbe5_miniproject.user.User;
import com.example.kdtbe5_miniproject.user.UserPosition;
import com.example.kdtbe5_miniproject.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class DayOffService {
    private final DayOffRepository dayOffRepository;
    private final UserRepository userRepository;

    // 연차 신청 마감일
    private final LocalDate applicationDeadline = LocalDate.of(2023, 12, 31);

    // 연차 신청
    // TODO 이미 신청중인 날짜에 중복신청 하는 경우 예외처리
    @Transactional
    public void registerDayOff(Long userId, DayOffRequest.RegisterDTO registerDTO) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("사용자를 찾을 수 없습니다."));

        List<DayOff> dayOffs = dayOffRepository.findByUser(user);

        // 동일한 날짜에 신청했는지 체크
        for (DayOff dayOff : dayOffs) {
            if (!((dayOff.getEndDate().isBefore(registerDTO.getStartDate()) ||
                    dayOff.getEndDate().isEqual(registerDTO.getStartDate())) ||
                    (dayOff.getStartDate().isAfter(registerDTO.getEndDate()) ||
                            dayOff.getStartDate().isEqual(registerDTO.getEndDate())))) {

                // 동일한 날짜에 반차 두번 신청하는 경우를 제한
                if (dayOff.getType() != DayOffType.연차) {
                    throw new IllegalArgumentException("동일한 날짜에 반차를 두 번 신청할 수 없습니다.");
                } else if (registerDTO.getType() != DayOffType.연차) {
                    throw new IllegalArgumentException("신청하려는 날짜가 이미 등록된 연차와 겹칩니다.");
                }
            }
        }

        // 연차 신청 마감일 체크
        if (LocalDate.now().isAfter(applicationDeadline)) {
            throw new IllegalArgumentException("연차 신청 마감일이 지났습니다.");
        }

        // 연차 유형에 따른 신청일 계산
        float appliedDayOff = 1.0f;
        if (registerDTO.getType() == DayOffType.연차) {
            appliedDayOff = registerDTO.getStartDate().datesUntil(registerDTO.getEndDate()).filter(d -> d.getDayOfWeek().getValue() < 6).count();
        } else if (registerDTO.getType() == DayOffType.오전반차 || registerDTO.getType() == DayOffType.오후반차) {
            // 반차일 경우 총 연차에서 0.5 차감
            appliedDayOff = 0.5f;
        }

        // 지난 날짜에 대한 연차 신청 체크
        if (registerDTO.getStartDate().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("지난 날짜에 대한 연차 신청은 불가능합니다.");
        }

        // 남은 휴가 확인
        float totalDayOff = user.determineInitialDayOff();
        float usedDayOff = 0f;

        for (DayOff dayOff : dayOffs) {
            usedDayOff += dayOff.getStartDate().datesUntil(dayOff.getEndDate()).filter(d -> d.getDayOfWeek().getValue() < 6).count();
        }

        float remainingDayOff = totalDayOff - usedDayOff - appliedDayOff;

        if (remainingDayOff < 0) {
            throw new IllegalArgumentException("남은 연차가 부족합니다.");
        }

        DayOff dayOff = registerDTO.toEntity(user, appliedDayOff);
        dayOffRepository.save(dayOff);
    }

    // 나의 남은 연차 (초기 연차만 설정)
    @Transactional(readOnly = true)
    public DayOffResponse.MyDayOffDTO myDayOffInfo(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("사용자를 찾을 수 없습니다."));

        float totalDayOff = user.determineInitialDayOff();
        float usedDayOff = 0;
        List<DayOff> dayOffs = dayOffRepository.findByUser(user);

        for(DayOff dayOff : dayOffs){
            if (dayOff.getStatus() == DayOffStatus.승인 || dayOff.getStatus() == DayOffStatus.대기) {
                usedDayOff += dayOff.getUsedDayOff();
            }
        }

        float remainingDayOff = totalDayOff - usedDayOff;
        return new DayOffResponse.MyDayOffDTO(totalDayOff, usedDayOff, remainingDayOff);
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

        // 본인이 신청한 연차만 취소 가능하도록 체크
        if (!dayOff.getUser().getId().equals(userId)) {
            throw new IllegalArgumentException("본인이 신청한 연차만 취소할 수 있습니다.");
        }

        // 연차 신청의 상태를 취소로 변경
        if (dayOff.getStatus() == DayOffStatus.승인 || dayOff.getStatus() == DayOffStatus.반려) {
            throw new IllegalArgumentException("이미 처리된 연차 신청은 취소할 수 없습니다.");
        }

        // 취소된 연차를 삭제
        dayOffRepository.delete(dayOff);
    }
}
