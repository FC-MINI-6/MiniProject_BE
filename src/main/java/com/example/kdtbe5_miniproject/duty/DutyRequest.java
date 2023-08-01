package com.example.kdtbe5_miniproject.duty;

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
    }
}