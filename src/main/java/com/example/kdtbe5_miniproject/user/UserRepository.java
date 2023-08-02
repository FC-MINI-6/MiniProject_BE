package com.example.kdtbe5_miniproject.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    Optional<User> findByEmail(@Param("email") String email);

    @Modifying
    @Query(value = "UPDATE User SET password = :newPassword, updateDate = :now WHERE id = :id")
    void updateById(@Param("newPassword") String newPassword, @Param("now")LocalDateTime now, @Param("id") Long id);
}
