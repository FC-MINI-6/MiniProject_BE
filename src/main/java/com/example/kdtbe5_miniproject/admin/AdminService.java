package com.example.kdtbe5_miniproject.admin;

import com.example.kdtbe5_miniproject.dayoff.DayOffStatus;
import com.example.kdtbe5_miniproject.dayoff.DayOffType;
import com.example.kdtbe5_miniproject.duty.DutyStatus;
import com.example.kdtbe5_miniproject.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AdminService {

    private final AdminRepository adminRepository;

    // 승인 대기중인 연차 목록
    @Transactional
    public List<AdminResponse.DayOffStatusDTO> findWaitingDayOff() {
        DayOffStatus status = DayOffStatus.대기;
        return adminRepository.findDayOffByStatus(status).stream().map(AdminResponse.DayOffStatusDTO::new).collect(Collectors.toList());
    }

    // 승인 대기중인 당직 목록
    @Transactional
    public List<AdminResponse.DutyStatusDTO> findWaitingDuty() {
        DutyStatus status = DutyStatus.대기;
        return adminRepository.findDutyByStatus(status).stream().map(AdminResponse.DutyStatusDTO::new).collect(Collectors.toList());
    }

    // 전체 사용자 세부정보
    @Transactional
    public List<AdminResponse.UsersDTO> findAllUsers() {
        return adminRepository.findAllUsers().stream().map(AdminResponse.UsersDTO::new).collect(Collectors.toList());
    }

    // 특정 사용자 세부정보
    @Transactional
    public AdminResponse.UsersDTO findUserDetail(Long userId) {
        return new AdminResponse.UsersDTO(adminRepository.findUserById(userId));
    }


    @Transactional
    public void modifyDayOffStatus(Long id, AdminRequest.TreatDayOffDTO request) {
        adminRepository.updateNumOfDayOffById(id, request.getStatus(), LocalDate.now());
    }

    @Transactional
    public List<Long> findWaitingDayOffList(Long id) {
        Long userId = adminRepository.findDayOffById(id).getUser().getId();
        return adminRepository.findDayOffByStatus(userId, DayOffStatus.대기);
    }

    //TODO 날짜 중복 예외
    @Transactional
    public void modifyDutyStatus(Long id, AdminRequest.TreatDutyDTO request) {
        adminRepository.updateDutyById(id, request.getStatus(), LocalDate.now());
    }

    @Transactional
    public void modifyUser(Long id, AdminRequest.UserDetailsDTO request) {
        adminRepository.updateUserById(id, request.getPhoneNumber(), request.getPosition(), request.getRoles());
    }
}
