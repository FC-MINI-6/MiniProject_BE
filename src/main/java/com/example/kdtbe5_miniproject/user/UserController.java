package com.example.kdtbe5_miniproject.user;

import com.example.kdtbe5_miniproject._core.errors.exception.Exception401;
import com.example.kdtbe5_miniproject._core.security.CustomUserDetails;
import com.example.kdtbe5_miniproject._core.security.JwtTokenProvider;
import com.example.kdtbe5_miniproject._core.util.ApiUtils;
import com.example.kdtbe5_miniproject._core.util.EncryptUtils;
import com.example.kdtbe5_miniproject.login_history.LoginHistoryRequest;
import com.example.kdtbe5_miniproject.login_history.LoginHistoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

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
        return ResponseEntity.ok().body(ApiUtils.success(responseDTO, HttpStatus.OK));
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

            Map<String, Object> response = new HashMap<>();
            response.put("accessToken", jwt);

            User user = myUserDetails.getUser();
            UserResponse.JoinDTO userResponse = new UserResponse.JoinDTO(user);

            response.put("user", userResponse);

            return ResponseEntity.ok().header("Authorization", jwt).body(ApiUtils.success(response, HttpStatus.OK));

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
}