package com.example.kdtbe5_miniproject.schedule;

import com.example.kdtbe5_miniproject.dayoff.DayOff;
import com.example.kdtbe5_miniproject.dayoff.DayOffStatus;
import com.example.kdtbe5_miniproject.duty.Duty;
import com.example.kdtbe5_miniproject.duty.DutyStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ScheduleRepository {

    private final EntityManager entityManager;

    // 연차
    // 승인 status로 조회
    // 날짜 조회 추가 필요
    public List<DayOff> filterDayOffSchedule(DayOffStatus status) {
        Query query = entityManager.createQuery(
                "SELECT d FROM DayOff d INNER JOIN d.user u WHERE d.status = :status", DayOff.class);
        query.setParameter("status", status);

        return query.getResultList();
    }

    // 당직
    // 승인 status로 조회
    // 날짜 조회 추가 필요
    public List<Duty> filterDutySchedule(DutyStatus status) {
        Query query = entityManager.createQuery(
                "SELECT d FROM Duty d INNER JOIN d.user u WHERE d.status = :status", Duty.class);
        query.setParameter("status", status);

        return query.getResultList();
    }


}
