package com.example.kdtbe5_miniproject.admin;

import com.example.kdtbe5_miniproject.dayoff.DayOff;
import com.example.kdtbe5_miniproject.dayoff.DayOffStatus;
import com.example.kdtbe5_miniproject.dayoff.DayOffType;
import com.example.kdtbe5_miniproject.duty.DutyStatus;
import com.example.kdtbe5_miniproject.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AdminService {

    private final AdminRepository adminRepository;

    @Transactional
    public List<AdminResponse.DayOffStatusDTO> findWaitingDayOff() {
        DayOffStatus status = DayOffStatus.대기;
        return adminRepository.findDayOffByStatus(status).stream().map(AdminResponse.DayOffStatusDTO::new).collect(Collectors.toList());
    }

    @Transactional
    public List<AdminResponse.DutyStatusDTO> findWaitingDuty() {
        DutyStatus status = DutyStatus.대기;
        return adminRepository.findDutyByStatus(status).stream().map(AdminResponse.DutyStatusDTO::new).collect(Collectors.toList());
    }

    @Transactional
    public List<AdminResponse.UsersDTO> findAllUsers() {
        List<AdminResponse.UsersDTO> userList = new ArrayList<>();

        List<Object[]> resultList = adminRepository.findAllUsers();
        for (Object[] result : resultList) {
            AdminResponse.UsersDTO user = new AdminResponse.UsersDTO((User) result[0]);
            user.setNumOfDayOff((Float) result[1]);
            userList.add(user);
        }
        return userList;
    }

    @Transactional
    public AdminResponse.UsersDTO findUserDetail(Long userId) {
        Object[] result = adminRepository.findUserById(userId);
        AdminResponse.UsersDTO user = new AdminResponse.UsersDTO((User) result[0]);
        user.setNumOfDayOff((Float) result[1]);

        return user;
    }

    //TODO 음수일때 예외처리
    @Transactional
    public void modifyDayOffStatus(Long id, AdminRequest.TreatDayOffDTO request) {
        Float deduction = 0F;
        if (request.getStatus().equals(DayOffStatus.승인)) {
            AdminResponse.DayOffInfoDTO dayOff = new AdminResponse.DayOffInfoDTO(adminRepository.findDayOffById(id));
            if (dayOff.getType().equals(DayOffType.연차)) {
                deduction = Float.valueOf(ChronoUnit.DAYS.between(dayOff.getStartDate(), dayOff.getEndDate()));
                verifyApproval(deduction, dayOff.getNumOfDayOff());
            } else if (dayOff.getType().equals(DayOffType.오전반차) || dayOff.getType().equals(DayOffType.오후반차)) {
                deduction = 0.5F;
            }
        }
        adminRepository.updateNumOfDayOffById(id, deduction, request.getStatus());
    }

    private boolean verifyApproval(Float deduction, Float numOfDayOff) {
        if (numOfDayOff - deduction < 0) {
            throw new RuntimeException("남은 연차 일 수가 모자랍니다.");
        }
        return true;
    }

    //TODO 날짜 중복 예외
    @Transactional
    public void modifyDutyStatus(Long id, AdminRequest.TreatDutyDTO request) {
        adminRepository.updateDutyById(id, request.getStatus());
    }

    @Transactional
    public void modifyUser(Long id, AdminRequest.UserDetailsDTO request) {
        adminRepository.updateUserById(id, request.getPhoneNumber(), request.getPosition(), request.getRoles());
    }
}
