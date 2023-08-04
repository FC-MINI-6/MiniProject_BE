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
    private float remainingDayOff;  // 남은 연차 수

    public void updatePhoneNumber(String newPhoneNum) {
        this.phoneNumber = newPhoneNum;
    }
    public void setRemainingDayOff(float remainingDayOff) {
        this.remainingDayOff = remainingDayOff;
    }

    public UserResponse.LoginDTO toEntity() {
        return UserResponse.LoginDTO.builder()
                .id(this.id)
                .username(this.username)
                .email(this.email)
                .phoneNumber(this.phoneNumber)
                .position(this.position)
                .roles(this.roles)
                .joinDate(this.joinDate)
                .build();
    }

    // 직급별 연차 계산
    public int determineInitialDayOff() {
        // 입사일로부터 1년이 지났는지 체크
        LocalDate now = LocalDate.now();
        LocalDate oneYearAfterJoinDate = this.getJoinDate().plusYears(1);
        int initialDayOff;

        if (now.isBefore(oneYearAfterJoinDate)) { // 입사 후 1년 미만
            int monthOfJoin = this.getJoinDate().getMonthValue();
            initialDayOff = 11 - (monthOfJoin - 1); // 입사 월 기준으로 연차 계산
        } else { // 입사 후 1년 이상
            int position = this.getPosition().getTypeNumber();
            if (position == 0) {
                initialDayOff = 15;  // 사원
            } else if (position == 1) {
                initialDayOff = 17;  // 주임
            } else if (position == 2) {
                initialDayOff = 19;  // 대리
            } else if (position == 3) {
                initialDayOff = 21;  // 과장
            } else if (position == 4) {
                initialDayOff = 23;  // 차장
            } else if (position == 5) {
                initialDayOff = 25;  // 부장
            } else {
                throw new IllegalArgumentException("직급: " + position);
            }
        }

        return initialDayOff;
    }
}
