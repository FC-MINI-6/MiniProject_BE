package com.example.kdtbe5_miniproject.duty;

import com.example.kdtbe5_miniproject.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class DutyRequest {
    @Getter
    @Setter
    public static class DutyDTO {
        @NotNull
        private LocalDate date;
        @NotEmpty
        private String reason;
        private LocalDate applyAt;

        public Duty toEntity(User user) {
            return Duty.builder()
                    .date(this.date)
                    .reason(this.reason)
                    .status(DutyStatus.valueOf("대기"))
                    .applyAt(LocalDate.now())
                    .user(user)
                    .build();
        }
    }
}