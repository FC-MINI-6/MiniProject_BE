package com.example.kdtbe5_miniproject.dayoff;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class DayOffResponse {

    @Getter
    public static class MyDayOffDTO {
        private float numOfInitialDayOff;

        /*
        public MyDayOffDTO(float valid, float used, float expired) {
            this.valid = valid;
        }
        */

        public MyDayOffDTO(float numOfInitialDayOff) {
            this.numOfInitialDayOff = numOfInitialDayOff;
        }
    }

    @Getter
    public static class AppliedDayOffDTO {
        private Long id;
        private int type; // 숫자로 변경
        private LocalDate startDate;
        private LocalDate endDate;
        private String reason;
        private int status; // 숫자로 변경

        public AppliedDayOffDTO(DayOff dayOff) {
            this.id = dayOff.getId();
            this.type = dayOff.getType().getValue(); // Enum의 숫자 값을 얻어옴
            this.startDate = dayOff.getStartDate();
            this.endDate = dayOff.getEndDate();
            this.reason = dayOff.getReason();
            this.status = dayOff.getStatus().getValue(); // Enum의 숫자 값을 얻어옴
        }
    }
}
