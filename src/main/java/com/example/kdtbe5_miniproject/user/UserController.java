package com.example.kdtbe5_miniproject.user;

import com.example.kdtbe5_miniproject._core.errors.exception.Exception401;
import com.example.kdtbe5_miniproject._core.security.CustomUserDetails;
import com.example.kdtbe5_miniproject._core.security.JwtTokenProvider;
import com.example.kdtbe5_miniproject._core.util.ApiUtils;
import com.example.kdtbe5_miniproject.login_history.LoginHistoryRequest;
import com.example.kdtbe5_miniproject.login_history.LoginHistoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@RestController
@Slf4j
public class UserController {

    private final LoginHistoryService loginHistoryService;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<?> join(@RequestBody @Valid UserRequest.JoinDTO joinDTO, Errors errors) {
        UserResponse.JoinDTO responseDTO = userService.joinUser(joinDTO);
        log.info(joinDTO.getUsername() + " Joined");
        return ResponseEntity.ok().body(ApiUtils.success(responseDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid UserRequest.LoginDTO loginDTO, Errors errors, HttpServletRequest request) {
        try {
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                    = new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword());
            Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
            CustomUserDetails myUserDetails = (CustomUserDetails) authentication.getPrincipal();

            String jwt = JwtTokenProvider.create(myUserDetails.getUser());
            addLoginHistory(request, myUserDetails);

            return ResponseEntity.ok().header("Authorization", jwt).body(ApiUtils.success(null));

        } catch (Exception e) {
            throw new Exception401("인증되지 않았습니다");
        }
    }

    private void addLoginHistory(HttpServletRequest request, CustomUserDetails myUserDetails) {
        LoginHistoryRequest.LoginHistoryDTO loginHistoryDTO = new LoginHistoryRequest.LoginHistoryDTO();

        loginHistoryDTO.setUser(myUserDetails.getUser());
        loginHistoryDTO.setIp(request.getRemoteAddr());
        loginHistoryDTO.setUserAgent(request.getHeader("User-Agent"));
        loginHistoryDTO.setLoginAt(LocalDateTime.now());

        loginHistoryService.save(loginHistoryDTO);
    }


    @PutMapping("/mypage/updatePhoneNumber")
    public ResponseEntity<?> updatePhoneNumber(@AuthenticationPrincipal CustomUserDetails updateUser, @RequestBody @Valid UserRequest.UpdateDTO updateDTO) {
        userService.updatePhoneNumber(updateUser.getUserId(), updateDTO);
        return ResponseEntity.ok().body(ApiUtils.success("전화번호가 변경되었습니다."));
    }


    @PutMapping("/mypage/updatePassword")
    public ResponseEntity<?> userPasswordModify(@RequestBody UserRequest.ModifyPwdDTO request) {
        userService.updatePwd(request);
        return ResponseEntity.ok().body(ApiUtils.success("변경되었습니다."));
    }

}