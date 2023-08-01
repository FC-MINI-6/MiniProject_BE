package com.example.kdtbe5_miniproject.duty;

import com.example.kdtbe5_miniproject._core.security.CustomUserDetails;
import com.example.kdtbe5_miniproject._core.util.ApiUtils;
import com.example.kdtbe5_miniproject.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/mypage")
@RequiredArgsConstructor
@RestController
public class DutyController {

    private final DutyService dutyService;

    // 당직 신청
    @PostMapping("/duty/register")
    public ResponseEntity<?> createDuty(@RequestBody @Valid DutyRequest.DutyDTO dutyDTO, @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        User user = customUserDetails.getUser();
        DutyResponse.DutyDTO responseDTO = dutyService.createDuty(dutyDTO, user.getId());
        return ResponseEntity.ok().body(ApiUtils.success(responseDTO));
    }

    // 내 당직 리스트
    @GetMapping("/duty/applied")
    public ResponseEntity<?> getAppliedDuty(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        User user = customUserDetails.getUser();
        List<DutyResponse.DutyDTO> appliedDuties = dutyService.getAppliedDuties(user.getId());
        return ResponseEntity.ok().body(ApiUtils.success(appliedDuties));
    }

    // 당직 신청 취소
    @DeleteMapping("/duty/{dutyId}")
    public ResponseEntity<?> deleteDuty(@PathVariable Long dutyId, @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        User user = customUserDetails.getUser();
        dutyService.deleteDuty(dutyId, user.getId());
        return ResponseEntity.ok().body(ApiUtils.success("당직 신청이 취소되었습니다."));
    }
}