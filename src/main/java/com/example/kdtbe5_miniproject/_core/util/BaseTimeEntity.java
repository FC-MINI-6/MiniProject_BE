package com.example.kdtbe5_miniproject._core.util;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseTimeEntity extends DateUtils{

    @LastModifiedDate
    private LocalDateTime updateDate; //TODO 초가 소수점 단위로 생성되어서 저장됨, 소수점 이하 버리기
}
