package com.example.kdtbe5_miniproject.user;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
public class UserResponse {
    @Getter
    @Setter
    public static class JoinDTO {
        private Long id;
        private String email;
        private String username;
        private String phoneNumber;
        private int position;
        private int roles;
        private Float numOfDayOff;
        private LocalDate joinDate;
        public JoinDTO(User user) {
            this.id = user.getId();
            this.email = user.getEmail();
            this.username = user.getUsername();
            this.phoneNumber = user.getPhoneNumber();
            this.position = user.getPosition().getTypeNumber();
            this.roles = user.getRoles().getTypeNumber();
            this.joinDate = user.getJoinDate();
        }
    }

    @Getter
    public static class AllUsersDTO {
        private Long id;
        private String username;
        private int position;

        public AllUsersDTO(User user) {
            this.id = user.getId();
            this.username = user.getUsername();
            this.position = user.getPosition().getTypeNumber();
        }
    }

    @Getter
    @Setter
    @Builder
    public static class LoginDTO {
        private Long id;
        private String username;
        private String email;
        private String phoneNumber;
        private UserPosition position;
        private UserRoles roles;
        private LocalDate joinDate;
        private String accessToken;
    }

}