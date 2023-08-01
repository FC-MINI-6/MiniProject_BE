package com.example.kdtbe5_miniproject.dayoff;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class DayOffResponse {

    @Getter
    public static class MyDayOffDTO {
        private int valid;
        private int expired;
        private int used;

        public MyDayOffDTO(int valid, int expired, int used) {
            this.valid = valid;
            this.expired = expired;
            this.used = used;
        }
    }

    @Getter
    public static class AppliedDayOffDTO {
        private Long id;
        private String type;
        private LocalDate startDate;
        private LocalDate endDate;
        private String reason;
        private String status;

        public AppliedDayOffDTO(DayOff dayOff) {
            this.id = dayOff.getId();
            this.type = dayOff.getType();
            this.startDate = dayOff.getStartDate();
            this.endDate = dayOff.getEndDate();
            this.reason = dayOff.getReason();
            this.status = dayOff.getStatus();
        }
    }
}
