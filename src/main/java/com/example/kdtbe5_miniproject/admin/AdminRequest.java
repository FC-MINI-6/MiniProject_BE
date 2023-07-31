package com.example.kdtbe5_miniproject.admin;

import lombok.Getter;
import lombok.Setter;

public class AdminRequest {

    @Getter
    @Setter
    public static class TreatDTO {
        private String status;
    }

    @Getter
    @Setter
    public static class UserDetailsDTO {
        private String phoneNumber;
        private String position;
        private String roles;
    }
}
