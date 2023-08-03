package com.example.kdtbe5_miniproject.admin;

import com.example.kdtbe5_miniproject._core.util.ApiUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin")
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
    public ResponseEntity<?> dayOffModify(@PathVariable Long dayOffId, @RequestBody AdminRequest.TreatDayOffDTO request) {
        if (adminService.findWaitingDayOffList(dayOffId).get(0) != dayOffId) {
            throw new RuntimeException("이미 처리된 요청이거나, 해당 사용자의 이전 요청을 먼저 처리해주세요.");
        }
        adminService.modifyDayOffStatus(dayOffId, request);

        return ResponseEntity.ok().body(ApiUtils.success(request.getStatus() + "되었습니다."));
    }

    @PutMapping("/status/duty/{dutyId}")
    public ResponseEntity<?> dutyModify(@PathVariable Long dutyId, @RequestBody AdminRequest.TreatDutyDTO request) {
        adminService.modifyDutyStatus(dutyId, request);

        return ResponseEntity.ok().body(ApiUtils.success(request.getStatus() + "되었습니다."));
    }

    @PutMapping("/users/update/{userId}")
    public ResponseEntity<?> userModify(@PathVariable Long userId, @RequestBody AdminRequest.UserDetailsDTO request) {
        adminService.modifyUser(userId, request);

        return ResponseEntity.ok().body(ApiUtils.success("수정되었습니다."));
    }
}
