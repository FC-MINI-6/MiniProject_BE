package com.example.kdtbe5_miniproject.admin;

import com.example.kdtbe5_miniproject.dayoff.DayOffStatus;
import com.example.kdtbe5_miniproject.dayoff.DayOffType;
import com.example.kdtbe5_miniproject.duty.DutyStatus;
import com.example.kdtbe5_miniproject.user.UserPosition;
import com.example.kdtbe5_miniproject.user.UserRoles;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

public class AdminRequest {

    @Getter
    @Setter
    public static class TreatDayOffDTO {
        @NotEmpty
        private DayOffStatus status;
    }

    @Getter
    @Setter
    public static class TreatDutyDTO {
        @NotEmpty
        private DutyStatus status;
    }

    @Getter
    @Setter
    public static class UserDetailsDTO {
        @NotEmpty
        private String phoneNumber;
        @NotEmpty
        private UserPosition position;
        @NotEmpty
        private UserRoles roles;
    }
}
