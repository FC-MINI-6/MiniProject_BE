package com.example.kdtbe5_miniproject.dayoff;

import com.example.kdtbe5_miniproject.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

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
    private String type;    // 연차, 오전반차, 오후반차
    private String status;  // 대기, 승인, 반려
    private Long numOfDayOff;
}