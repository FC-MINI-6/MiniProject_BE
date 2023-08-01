package com.example.kdtbe5_miniproject.dayoff;

import com.example.kdtbe5_miniproject.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public class DayOffRequest {

    @Getter
    @Setter
    public static class RegisterDTO {
        @NotNull
        private String type;
        @NotNull
        private LocalDate startDate;
        @NotNull
        private LocalDate endDate;
        @NotNull
        private String reason;

        public DayOff toEntity(User user) {
            return DayOff.builder()
                    .type(this.type)
                    .startDate(this.startDate)
                    .endDate(this.endDate)
                    .reason(this.reason)
                    .user(user)
                    .build();
        }
    }
}
