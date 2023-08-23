package com.example.kdtbe5_miniproject.duty;

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
@Table(name = "duty_tb")
public class Duty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    private LocalDate date;
    private String reason;
    private DutyStatus status;  //대기(0), 승인(1), 반려(2)
    private LocalDate applyAt;
    private LocalDate processAt;
}