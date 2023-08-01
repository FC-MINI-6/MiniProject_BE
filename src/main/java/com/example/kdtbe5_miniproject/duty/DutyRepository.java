package com.example.kdtbe5_miniproject.duty;

import com.example.kdtbe5_miniproject.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DutyRepository extends JpaRepository<Duty, Long> {

    Optional<Duty> findByIdAndUser(Long id, User user);
    List<Duty> findAllByUser(User user);
}
