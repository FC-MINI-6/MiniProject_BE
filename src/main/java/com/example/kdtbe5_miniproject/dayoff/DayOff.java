package com.example.kdtbe5_miniproject.dayoff;

import com.example.kdtbe5_miniproject.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Table(name = "dayoff_tb")
public class DayOff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    private LocalDate startDate;
    private LocalDate endDate;
    private String reason;
    private DayOffType type;    //연차(0), 오전반차(1), 오후반차(2)
    private DayOffStatus status;  //대기(0), 승인(1), 반려(2)
    private Float numOfDayOff;
    private LocalDate applyAt;
    private LocalDate processAt;

    public float getUsedDayOff() {
        if (this.status != null && this.status == DayOffStatus.valueOf("승인")) {
            return this.numOfDayOff;
        } else {
            return 0f;
        }
    }
    public void setStatus(DayOffStatus status) {
        this.status = status;
    }
    public void setNumOfDayOff(float numOfDayOff) {
        this.numOfDayOff = numOfDayOff;
    }
}