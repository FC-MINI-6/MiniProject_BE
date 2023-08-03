package com.example.kdtbe5_miniproject.user;

import com.example.kdtbe5_miniproject._core.util.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.function.LongConsumer;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Table(name = "user_tb")
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    private UserPosition position;    //사원(0), 주임(1), 대리(2), 과장(3), 차장(4), 부장(5)
    private UserRoles roles;       //일반(0), 관리자(1)
    private LocalDate joinDate;

    public void updatePhoneNumber(String newPhoneNum) {
        this.phoneNumber = newPhoneNum;
    }

    // 직급별 연차 계산
    public int determineInitialDayOff() {
        int position = this.getPosition().getTypeNumber();
        if (position == 0) {
            return 15;                  // 사원
        } else if (position == 1) {
            return 17;                  // 주임
        } else if (position == 2) {
            return 20;                  // 대리
        } else if (position == 3) {
            return 22;                  // 과장
        } else if (position == 4) {
            return 23;                  // 차장
        } else if (position == 5) {
            return 26;                  // 부장
        } else {
            throw new IllegalArgumentException("직급: " + position);
        }
    }
}
