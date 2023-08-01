package com.example.kdtbe5_miniproject.dayoff;

import com.example.kdtbe5_miniproject.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DayOffRepository extends JpaRepository<DayOff, Long> {
    List<DayOff> findByUser(User user);
}
