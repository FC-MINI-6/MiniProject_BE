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
        private LocalDate startDate;
        private LocalDate endDate;
        private String reason;
        private DayOffType type;
        private DayOffStatus status;

        public DayOffStatusDTO(DayOff dayOff) {
            this.id = dayOff.getId();
            this.userId = dayOff.getUser().getId();
            this.userName = dayOff.getUser().getUsername();
            this.startDate = dayOff.getStartDate();
            this.endDate = dayOff.getEndDate();
            this.reason = dayOff.getReason();
            this.type = dayOff.getType();
            this.status = dayOff.getStatus();
        }
    }

    @Getter
    @Setter
    public static class DayOffInfoDTO {
        private Long id;
        private DayOffType type;
        private Float numOfDayOff;
        private LocalDate startDate;
        private LocalDate endDate;

        public DayOffInfoDTO(DayOff dayOff) {
            this.id = dayOff.getId();
            this.type = dayOff.getType();
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
        private LocalDate date;
        private String reason;
        private DutyStatus status;

        public DutyStatusDTO(Duty duty) {
            this.id = duty.getId();
            this.userId = duty.getUser().getId();
            this.username = duty.getUser().getUsername();
            this.date = duty.getDate();
            this.reason = duty.getReason();
            this.status = duty.getStatus();
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
        private UserPosition position;
        private UserRoles roles;
        private Float numOfDayOff;

        public UsersDTO(User user) {
            this.id = user.getId();
            this.username = user.getUsername();
            this.email = user.getEmail();
            this.phoneNumber = user.getPhoneNumber();
            this.joinDate = user.getJoinDate();
            this.position = user.getPosition();
            this.roles = user.getRoles();
        }
    }
}
