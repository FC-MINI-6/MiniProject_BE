package com.example.kdtbe5_miniproject.schedule;

import com.example.kdtbe5_miniproject._core.util.ApiUtils;
import com.example.kdtbe5_miniproject.user.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

@RequestMapping("/mypage")
@RequiredArgsConstructor
@RestController
public class ScheduleController {

    private final ScheduleService scheduleService;

    // 연차 + 당직 스케줄
    @GetMapping("/schedule")
    public ResponseEntity<?> allList() {

        HashMap<String, List> lists = new HashMap<>();
        lists.put("dayOffList", scheduleService.findDayOffSchedule());
        lists.put("dutyList", scheduleService.findDutySchedule());

        return ResponseEntity.ok().body(ApiUtils.success(lists));
    }

    // 전체 사용자 목록 조회
    @GetMapping("/schedule/userList")
    public ResponseEntity<?> getAppliedDuty() {
        List<UserResponse.AllUsersDTO> allUsers = scheduleService.getAllUsers();
        return ResponseEntity.ok().body(ApiUtils.success(allUsers));
    }

}
