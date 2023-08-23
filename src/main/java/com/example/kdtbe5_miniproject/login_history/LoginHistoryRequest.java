package com.example.kdtbe5_miniproject.login_history;

import com.example.kdtbe5_miniproject.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

public class LoginHistoryRequest {

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class LoginHistoryDTO {
        private User user;
        private String ip;
        private String userAgent;
        private LocalDateTime loginAt;

        public LoginHistory toEntity() {
            return LoginHistory.builder()
                    .user(user)
                    .ip(ip)
                    .userAgent(userAgent)
                    .loginAt(loginAt)
                    .build();
        }
    }
}
