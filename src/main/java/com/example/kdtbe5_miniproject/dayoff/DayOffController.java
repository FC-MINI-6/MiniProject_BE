package com.example.kdtbe5_miniproject.dayoff;

import com.example.kdtbe5_miniproject._core.security.CustomUserDetails;
import com.example.kdtbe5_miniproject._core.util.ApiUtils;
import com.example.kdtbe5_miniproject.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class DayOffController {
    private final DayOffService dayOffService;

    @PostMapping("/dayoff/register")
    public ResponseEntity<?> registerDayOff(@RequestBody @Valid DayOffRequest.RegisterDTO registerDTO, @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        User user = customUserDetails.getUser();
        dayOffService.registerDayOff(user.getId(), registerDTO);
        return ResponseEntity.ok().body(ApiUtils.success("등록이 완료되었습니다."));
    }

    @GetMapping("/dayoff/my")
    public ResponseEntity<?> myDayOffInfo(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        User user = customUserDetails.getUser();
        DayOffResponse.MyDayOffDTO myDayOffDTO = dayOffService.myDayOffInfo(user.getId());
        return ResponseEntity.ok().body(ApiUtils.success(myDayOffDTO));
    }

    @GetMapping("/dayoff/applied")
    public ResponseEntity<?> myAppliedDayOffs(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        User user = customUserDetails.getUser();
        DayOffResponse.AppliedDayOffDTO[] appliedDayOffDTOs = dayOffService.myAppliedDayOffs(user.getId());
        return ResponseEntity.ok().body(ApiUtils.success(appliedDayOffDTOs));
    }

    @DeleteMapping("/dayoff/{dayoffId}")
    public ResponseEntity<?> cancelDayOff(@PathVariable Long dayoffId, @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        User user = customUserDetails.getUser();
        dayOffService.cancelDayOff(dayoffId, user.getId());
        return ResponseEntity.ok().body(ApiUtils.success("연차 신청이 취소되었습니다."));
    }
}
