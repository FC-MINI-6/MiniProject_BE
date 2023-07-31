package com.example.kdtbe5_miniproject.admin;

import com.example.kdtbe5_miniproject._core.util.ApiUtils;
import com.example.kdtbe5_miniproject.dayoff.DayOff;
import com.example.kdtbe5_miniproject.duty.Duty;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
@RestController
@Slf4j
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/status")
    public ResponseEntity<?> waitingStatusList() {

        HashMap<String, List> lists = new HashMap<>();
        lists.put("dayOffList", adminService.findWaitingDayOff());
        lists.put("dutyList", adminService.findWaitingDuty());

        return ResponseEntity.ok().body(ApiUtils.success(lists));
    }

    @GetMapping("/users")
    public ResponseEntity<?> AllUsersList() {
        return ResponseEntity.ok().body(ApiUtils.success(adminService.findAllUsers()));
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<?> userDetails(@PathVariable Long userId) {
        return ResponseEntity.ok().body(ApiUtils.success(adminService.findUserDetail(userId)));
    }

    @PutMapping("/status/dayoff/{dayOffId}")
    public ResponseEntity<?> dayOffModify(@PathVariable Long dayOffId, @RequestBody AdminRequest.TreatDTO request) {
        adminService.modifyStatus(DayOff.class, dayOffId, request);

        return ResponseEntity.ok().body(ApiUtils.success(request.getStatus() + "되었습니다."));
    }

    @PutMapping("/status/duty/{dutyId}")
    public ResponseEntity<?> dutyModify(@PathVariable Long dutyId, @RequestBody AdminRequest.TreatDTO request) {
        adminService.modifyStatus(Duty.class, dutyId, request);

        return ResponseEntity.ok().body(ApiUtils.success(request.getStatus() + "되었습니다."));
    }

    @PutMapping("/users/update/{userId}")
    public ResponseEntity<?> userModify(@PathVariable Long userId, @RequestBody AdminRequest.UserDetailsDTO request) {
        adminService.modifyUser(userId, request);

        return ResponseEntity.ok().body(ApiUtils.success("수정되었습니다."));
    }
}
