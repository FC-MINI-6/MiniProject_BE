package com.example.kdtbe5_miniproject.schedule;

import com.example.kdtbe5_miniproject.admin.AdminResponse;
import com.example.kdtbe5_miniproject.dayoff.DayOffStatus;
import com.example.kdtbe5_miniproject.duty.Duty;
import com.example.kdtbe5_miniproject.duty.DutyStatus;
import com.example.kdtbe5_miniproject.user.UserRepository;
import com.example.kdtbe5_miniproject.user.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ScheduleService {

    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;

    @Transactional
    public List<ScheduleResponse.DayOffScheduleDTO> findDayOffScheduleByMonth(int year, int month) {
        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = startDate.with(TemporalAdjusters.lastDayOfMonth());
        DayOffStatus status = DayOffStatus.valueOf("승인");

        return scheduleRepository.filterDayOffSchedule(status, startDate, endDate).stream()
                .map(ScheduleResponse.DayOffScheduleDTO::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<ScheduleResponse.DutyScheduleDTO> findDutyScheduleByMonth(int year, int month) {
        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = startDate.with(TemporalAdjusters.lastDayOfMonth());
        DutyStatus status = DutyStatus.valueOf("승인");

        return scheduleRepository.filterDutySchedule(status, startDate, endDate).stream()
                .map(ScheduleResponse.DutyScheduleDTO::new)
                .collect(Collectors.toList());
    }


    // 전체 사용자 목록 조회
    @Transactional
    public List<UserResponse.AllUsersDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(UserResponse.AllUsersDTO::new)
                .collect(Collectors.toList());
    }

}
