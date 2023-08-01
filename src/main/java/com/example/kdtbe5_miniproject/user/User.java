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
    private UserPosition position;    //사원(0), 대리(1), 과장(2), 차장(3), 부장(4)
    private UserRoles roles;       //일반(0), 관리자(1)
    private LocalDate joinDate;

    public void updatePhoneNumber(String newPhoneNum) {
        this.phoneNumber = newPhoneNum;
    }

}
