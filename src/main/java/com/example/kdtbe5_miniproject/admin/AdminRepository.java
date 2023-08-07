package com.example.kdtbe5_miniproject.admin;

import com.example.kdtbe5_miniproject.dayoff.DayOff;
import com.example.kdtbe5_miniproject.dayoff.DayOffStatus;
import com.example.kdtbe5_miniproject.duty.Duty;
import com.example.kdtbe5_miniproject.duty.DutyStatus;
import com.example.kdtbe5_miniproject.user.User;
import com.example.kdtbe5_miniproject.user.UserPosition;
import com.example.kdtbe5_miniproject.user.UserRoles;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class AdminRepository {

    private final EntityManager entityManager;

    public List<DayOff> findDayOffByStatus(DayOffStatus status) {
        Query query = entityManager.createQuery(
                "SELECT d FROM DayOff d INNER JOIN d.user u WHERE d.status = :status", DayOff.class);
        query.setParameter("status", status);

        return query.getResultList();
    }

    public List<Long> findDayOffByStatus(Long userId, DayOffStatus status) {
        Query query = entityManager.createQuery(
                "SELECT d.id FROM DayOff d INNER JOIN d.user u WHERE d.status = :status AND d.user.id = :userId ORDER BY d.applyAt ASC");
        query.setParameter("status", status);
        query.setParameter("userId", userId);

        return query.getResultList();
    }


    public DayOff findDayOffById(Long id) {
        Query query = entityManager.createQuery(
                "SELECT d FROM DayOff d WHERE d.id = :id", DayOff.class);
        query.setParameter("id", id);

        return (DayOff) query.getSingleResult();
    }

    public List<Duty> findDutyByStatus(DutyStatus status) {
        Query query = entityManager.createQuery(
                "SELECT d FROM Duty d INNER JOIN d.user u WHERE d.status = :status", Duty.class);
        query.setParameter("status", status);

        return query.getResultList();
    }

    //TODO 최근 날짜로 조회
    public List<User> findAllUsers() {
        //대기 상태인 연차 중 numOfDayOff가 가장 낮은 값으로 가져옴
        Query query = entityManager.createQuery(
                "SELECT u From User u", User.class);

        return query.getResultList();
    }

    public User findUserById(Long userId) {
        Query query = entityManager.createQuery(
                "SELECT u FROM User u WHERE u.id = :id", User.class);
        query.setParameter("id", userId);

        return (User) query.getSingleResult();
    }

    @Transactional
    public void updateNumOfDayOffById(Long id, DayOffStatus status, LocalDate now) {
        Query query = entityManager.createQuery(
                "UPDATE DayOff SET status = :status, processAt = :now WHERE id = :id");
        query.setParameter("id", id);
        query.setParameter("status", status);
        query.setParameter("now", now);
        query.executeUpdate();
    }

    @Transactional
    public void updateDutyById(Long id, DutyStatus status, LocalDate now) {
        Query query = entityManager.createQuery(
                "UPDATE Duty SET status = :status, processAt = :now WHERE id = :id");
        query.setParameter("id", id);
        query.setParameter("status", status);
        query.setParameter("now", now);
        query.executeUpdate();
    }

    @Transactional
    public void updateUserById(Long id, String phoneNumber, UserPosition position, UserRoles roles) {
        Query query = entityManager.createQuery(
                "UPDATE User SET phoneNumber = :phoneNumber, position = :position, roles = : roles WHERE id = :id");
        query.setParameter("id", id);
        query.setParameter("phoneNumber", phoneNumber);
        query.setParameter("position", position);
        query.setParameter("roles", roles);
        query.executeUpdate();
    }
}
