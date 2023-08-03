package com.example.kdtbe5_miniproject.schedule;

import com.example.kdtbe5_miniproject.dayoff.DayOff;
import com.example.kdtbe5_miniproject.duty.Duty;
import com.example.kdtbe5_miniproject.user.UserPosition;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

public class ScheduleResponse {

    @Getter
    @Setter
    public static class DayOffScheduleDTO {
        private Long id;
        private Long userId;
        private String username;
        private UserPosition position;
        private String reason;
        private LocalDate startDate;
        private LocalDate endDate;

        public DayOffScheduleDTO(DayOff dayOff) {
            this.id = dayOff.getId();
            this.userId = dayOff.getUser().getId();
            this.username = dayOff.getUser().getUsername();
            this.position = dayOff.getUser().getPosition();
            this.reason = dayOff.getReason();
            this.startDate = dayOff.getStartDate();
            this.endDate = dayOff.getEndDate();
        }
    }

    @Getter
    @Setter
    public static class DutyScheduleDTO {
        private Long id;
        private Long userId;
        private String username;
        private UserPosition position;
        private String reason;
        private LocalDate date;

        public DutyScheduleDTO(Duty duty) {
            this.id = duty.getId();
            this.userId = duty.getUser().getId();
            this.username = duty.getUser().getUsername();
            this.position = duty.getUser().getPosition();
            this.reason = duty.getReason();
            this.date = duty.getDate();
        }
    }
}
