package com.example.kdtbe5_miniproject.schedule;

import com.example.kdtbe5_miniproject._core.util.ApiUtils;
import com.example.kdtbe5_miniproject.user.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ScheduleController {

    private final ScheduleService scheduleService;

    //연도, 월별 전체 연차당직 리스트


    // 전체 사용자 목록 조회
    @GetMapping("/schedule/userList")
    public ResponseEntity<?> getAppliedDuty() {
        List<UserResponse.AllUsersDTO> allUsers = scheduleService.getAllUsers();
        return ResponseEntity.ok().body(ApiUtils.success(allUsers));
    }

}
