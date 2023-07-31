package com.example.kdtbe5_miniproject.admin;

import com.example.kdtbe5_miniproject.dayoff.DayOff;
import com.example.kdtbe5_miniproject.duty.Duty;
import com.example.kdtbe5_miniproject.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class AdminRepository {

    private final EntityManager entityManager;

    public List<DayOff> findDayOffByStatus(String status) {
        Query query = entityManager.createQuery(
                "SELECT d FROM DayOff d INNER JOIN d.user u WHERE d.status = :status", DayOff.class);
        query.setParameter("status", status);

        return query.getResultList();
    }

    public List<Duty> findDutyByStatus(String status) {
        Query query = entityManager.createQuery(
                "SELECT d FROM Duty d INNER JOIN d.user u WHERE d.status = :status", Duty.class);
        query.setParameter("status", status);

        return query.getResultList();
    }

    public List<User> findAllUsers() {
        Query query = entityManager.createQuery(
                "SELECT u FROM User u", User.class);

        return query.getResultList();
    }

    public User findUserById(Long userId) {
        Query query = entityManager.createQuery(
                "SELECT u FROM User u WHERE id = :id", User.class);
        query.setParameter("id", userId);

        return (User) query.getSingleResult();
    }

    @Transactional
    public void updateStatusById(Class<?> type, Long id, String status) {
        Query query = entityManager.createQuery(
                "UPDATE " + type.getSimpleName() + " SET status = :status WHERE id = :id");
        query.setParameter("id", id);
        query.setParameter("status", status);
        query.executeUpdate();
    }

    @Transactional
    public void updateUserById(Long id, String phoneNumber, String position, String roles) {
        Query query = entityManager.createQuery(
                "UPDATE User SET phoneNumber = :phoneNumber, position = :position, roles = : roles WHERE id = :id");
        query.setParameter("id", id);
        query.setParameter("phoneNumber", phoneNumber);
        query.setParameter("position", position);
        query.setParameter("roles", roles);
        query.executeUpdate();
    }
}
