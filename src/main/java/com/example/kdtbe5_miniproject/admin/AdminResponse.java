package com.example.kdtbe5_miniproject.admin;

import com.example.kdtbe5_miniproject.dayoff.DayOff;
import com.example.kdtbe5_miniproject.dayoff.DayOffStatus;
import com.example.kdtbe5_miniproject.dayoff.DayOffType;
import com.example.kdtbe5_miniproject.duty.Duty;
import com.example.kdtbe5_miniproject.duty.DutyStatus;
import com.example.kdtbe5_miniproject.user.User;
import com.example.kdtbe5_miniproject.user.UserPosition;
import com.example.kdtbe5_miniproject.user.UserRoles;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

public class AdminResponse {

    @Getter
    @Setter
    public static class DayOffStatusDTO {
        private Long id;
        private Long userId;
        private String userName;
        private int position;
        private LocalDate startDate;
        private LocalDate endDate;
        private String reason;
        private int type;
        private int status;

        public DayOffStatusDTO(DayOff dayOff) {
            this.id = dayOff.getId();
            this.userId = dayOff.getUser().getId();
            this.userName = dayOff.getUser().getUsername();
            this.position = dayOff.getUser().getPosition().getTypeNumber();
            this.startDate = dayOff.getStartDate();
            this.endDate = dayOff.getEndDate();
            this.reason = dayOff.getReason();
            this.type = dayOff.getType().getTypeNumber();
            this.status = dayOff.getStatus().getTypeNumber();
        }
    }

    @Getter
    @Setter
    public static class DayOffInfoDTO {
        private Long id;
        private int type;
        private Float numOfDayOff;
        private LocalDate startDate;
        private LocalDate endDate;

        public DayOffInfoDTO(DayOff dayOff) {
            this.id = dayOff.getId();
            this.type = dayOff.getType().getTypeNumber();
            this.numOfDayOff = dayOff.getNumOfDayOff();
            this.startDate = dayOff.getStartDate();
            this.endDate = dayOff.getEndDate();
        }
    }

    @Getter
    @Setter
    public static class DutyStatusDTO {
        private Long id;
        private Long userId;
        private String username;
        private int position;
        private LocalDate date;
        private String reason;
        private int status;

        public DutyStatusDTO(Duty duty) {
            this.id = duty.getId();
            this.userId = duty.getUser().getId();
            this.username = duty.getUser().getUsername();
            this.position = duty.getUser().getPosition().getTypeNumber();
            this.date = duty.getDate();
            this.reason = duty.getReason();
            this.status = duty.getStatus().getValue();
        }
    }

    @Getter
    @Setter
    public static class UsersDTO {
        private Long id;
        private String username;
        private String email;
        private String phoneNumber;
        private LocalDate joinDate;
        private int position;
        private int roles;

        public UsersDTO(User user) {
            this.id = user.getId();
            this.username = user.getUsername();
            this.email = user.getEmail();
            this.phoneNumber = user.getPhoneNumber();
            this.joinDate = user.getJoinDate();
            this.position = user.getPosition().getTypeNumber();
            this.roles = user.getRoles().getTypeNumber();
        }
    }
}
