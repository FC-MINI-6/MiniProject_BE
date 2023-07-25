package com.example.kdtbe5_miniproject.user;

import lombok.Getter;
import lombok.Setter;

public class UserResponse {

    @Getter
    @Setter
    public static class JoinDTO {
        private Long id;
        private String username;
        private String email;
        private String position;
        private String roles;

        public JoinDTO(User user) {
            this.id = user.getId();
            this.username = user.getUsername();
            this.email = user.getEmail();
            this.position = user.getPosition();
            this.roles = user.getRoles();
        }
    }
}
