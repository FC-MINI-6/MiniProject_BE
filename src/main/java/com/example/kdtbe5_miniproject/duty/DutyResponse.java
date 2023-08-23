package com.example.kdtbe5_miniproject.duty;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
public class DutyResponse {

    @Getter
    public static class DutyDTO {
        private Long id;
        private LocalDate date;
        private String reason;
        private int status; // 숫자로 변경

        public DutyDTO(Duty duty) {
            this.id = duty.getId();
            this.date = duty.getDate();
            this.reason = duty.getReason();
            this.status = duty.getStatus().getValue(); // Enum의 숫자 값을 얻어옴
        }
    }
}