package com.example.kdtbe5_miniproject.user;

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
        private String position;
        private String roles;
        private Float numOfDayOff;
        private LocalDate joinDate;

        public JoinDTO(User user) {
            this.id = user.getId();
            this.email = user.getEmail();
            this.username = user.getUsername();
            this.phoneNumber = user.getPhoneNumber();
            this.position = user.getPosition();
            this.roles = user.getRoles();
            this.joinDate = user.getJoinDate();
        }
    }
}
