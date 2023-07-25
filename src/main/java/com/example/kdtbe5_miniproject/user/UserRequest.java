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
        @Pattern(regexp = "^[a-zA-Z0-9]{2,20}$", message = "영문/숫자 2~20자 이내로 작성해주세요")
        @NotEmpty
        private String username;
        @NotEmpty
        @Size(min = 4, max = 20)
        private String password;
    }

    @Getter
    @Setter
    public static class JoinDTO {
        @NotEmpty
        @Pattern(regexp = "^[a-zA-Z0-9]{2,20}$", message = "영문/숫자 2~20자 이내로 작성해주세요")
        private String username;

        @NotEmpty
        @Size(min = 4, max = 20)
        private String password;

        @NotEmpty
        @Pattern(regexp = "^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$", message = "이메일 형식으로 작성해주세요")
        private String email;

        private String phoneNumber;
        private String position;
        private LocalDateTime joinDate;

        public User toEntity() {
            return User.builder()
                    .username(username)
                    .password(password)
                    .email(email)
                    .phoneNumber(phoneNumber)
                    .position(position)
                    .roles("USER")
                    .joinDate(joinDate)
                    .build();
        }
    }
}
