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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    // 회원가입
    @PostMapping("/join")
    public ResponseEntity<?> join(@RequestBody @Valid UserRequest.JoinDTO joinDTO, Errors errors) {
        userService.joinUser(joinDTO);
        log.info(joinDTO.getUsername() + " Joined");
        return ResponseEntity.ok().body(ApiUtils.success("회원가입이 완료되었습니다"));
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid UserRequest.LoginDTO loginDTO, Errors errors, HttpServletRequest request) {
        try {
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                    = new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword());
            Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
            CustomUserDetails myUserDetails = (CustomUserDetails) authentication.getPrincipal();

            String jwt = JwtTokenProvider.create(myUserDetails.getUser());
            addLoginHistory(request, myUserDetails);

            UserResponse.LoginDTO user = userService.loginInform(loginDTO);

            return ResponseEntity.ok().header("Authorization", jwt).body(ApiUtils.success(user));

        } catch (Exception e) {
            throw new Exception401("인증되지 않았습니다");
        }
    }

    // 로그인 기록
    private void addLoginHistory(HttpServletRequest request, CustomUserDetails myUserDetails) {
        LoginHistoryRequest.LoginHistoryDTO loginHistoryDTO = new LoginHistoryRequest.LoginHistoryDTO();

        loginHistoryDTO.setUser(myUserDetails.getUser());
        loginHistoryDTO.setIp(request.getRemoteAddr());
        loginHistoryDTO.setUserAgent(request.getHeader("User-Agent"));
        loginHistoryDTO.setLoginAt(LocalDateTime.now());

        loginHistoryService.save(loginHistoryDTO);
    }

    // 로그아웃
    @GetMapping(value = "/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        return "redirect:/login";
    }

    // 핸드폰 번호 변경
    @PutMapping("/mypage/updatePhoneNumber")
    public ResponseEntity<?> updatePhoneNumber(@AuthenticationPrincipal CustomUserDetails updateUser, @RequestBody @Valid UserRequest.UpdateDTO updateDTO) {
        userService.updatePhoneNumber(updateUser.getUserId(), updateDTO);
        return ResponseEntity.ok().body(ApiUtils.success("변경되었습니다."));
    }

    // 비밀번호 변경
    @PutMapping("/mypage/updatePassword")
    public ResponseEntity<?> userPasswordModify(@RequestBody UserRequest.ModifyPwdDTO request) {
        userService.updatePwd(request);
        return ResponseEntity.ok().body(ApiUtils.success("변경되었습니다."));
    }

}