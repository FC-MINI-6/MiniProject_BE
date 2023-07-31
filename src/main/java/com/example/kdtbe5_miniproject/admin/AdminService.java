package com.example.kdtbe5_miniproject.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AdminService {

    private final AdminRepository adminRepository;

    @Transactional
    public List<AdminResponse.DayOffStatusDTO> findWaitingDayOff() {
        String status = "대기";
        return adminRepository.findDayOffByStatus(status).stream().map(AdminResponse.DayOffStatusDTO::new).collect(Collectors.toList());
    }

    @Transactional
    public List<AdminResponse.DutyStatusDTO> findWaitingDuty() {
        String status = "대기";
        return adminRepository.findDutyByStatus(status).stream().map(AdminResponse.DutyStatusDTO::new).collect(Collectors.toList());
    }

    @Transactional
    public List<AdminResponse.UsersDTO> findAllUsers() {
        return adminRepository.findAllUsers().stream().map(AdminResponse.UsersDTO::new).collect(Collectors.toList());
    }

    @Transactional
    public AdminResponse.UsersDTO findUserDetail(Long userId) {
        return new AdminResponse.UsersDTO(adminRepository.findUserById(userId));
    }

    @Transactional
    public void modifyStatus(Class<?> type, Long id, AdminRequest.TreatDTO request) {
        adminRepository.updateStatusById(type, id, request.getStatus());
    }

    @Transactional
    public void modifyUser(Long id, AdminRequest.UserDetailsDTO request) {
        adminRepository.updateUserById(id, request.getPhoneNumber(), request.getPosition(), request.getRoles());
    }
}
