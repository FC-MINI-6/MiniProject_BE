package com.example.kdtbe5_miniproject.user;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class UserRequest {
    @Getter
    @Setter
    public static class LoginDTO {
        @NotEmpty
        private String email;
        @NotEmpty
        @Size(min = 4, max = 20)
        private String password;
    }

    @Getter
    @Setter
    public static class JoinDTO {
        @NotEmpty
        private String username;
        @NotEmpty
        @Size(min = 4, max = 20)
        private String password;
        @NotEmpty
        @Pattern(regexp = "^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$", message = "이메일 형식으로 작성해주세요")
        private String email;
        private String phoneNumber;
        private String position;
        private Long numOfDayOff;
        private LocalDateTime joinDate;

        public User toEntity() {
            return User.builder()
                    .username(username)
                    .password(password)
                    .email(email)
                    .phoneNumber(phoneNumber)
                    .position(position)
                    .numOfDayOff(numOfDayOff)
                    .roles("USER")
                    .joinDate(joinDate)
                    .build();
        }
    }
}
