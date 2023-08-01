package com.example.kdtbe5_miniproject.duty;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

public class DutyResponse {
    @Getter
    @Setter
    public static class DutyDTO {
        private Long id;
        private LocalDate date;
        private String reason;
        private String status;

        public DutyDTO(Duty duty) {
            this.id = duty.getId();
            this.date = duty.getDate();
            this.reason = duty.getReason();
            this.status = duty.getStatus();
        }
    }
}
