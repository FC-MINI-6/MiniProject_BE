package com.example.kdtbe5_miniproject.user;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;

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
        @Pattern(regexp = "^01(?:0|1|[6-9])\\d{7,8}$", message = "01011112222와 같은 형식으로 작성해주세요")
        private String phoneNumber;
        private UserPosition position;
        private Float numOfDayOff;
        private LocalDate joinDate;

        public User toEntity() {
            return User.builder()
                    .username(username)
                    .password(password)
                    .email(email)
                    .phoneNumber(phoneNumber)
                    .position(position)
                    .roles(UserRoles.valueOf("일반"))
                    .joinDate(joinDate)
                    .build();
        }
    }
    @Getter
    public static class UpdateDTO {
        @NotEmpty
        @Pattern(regexp = "^01(?:0|1|[6-9])\\d{7,8}$", message = "01011112222와 같은 형식으로 작성해주세요")
        private String phoneNumber;
    }

    @Getter
    @Setter
    public static class ModifyPwdDTO {
        private Long userId;
        private String oldPassword;
        private String newPassword;
    }
}
