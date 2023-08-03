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
            AdminResponse.UsersDTO user = new AdminResponse.UsersDTO((User) result[1]);
            user.setNumOfDayOff((Float) result[2]);
            userList.add(user);
        }
        return userList;
    }

    @Transactional
    public AdminResponse.UsersDTO findUserDetail(Long userId) {
        Object[] result = adminRepository.findUserById(userId);
        AdminResponse.UsersDTO user = new AdminResponse.UsersDTO((User) result[1]);
        user.setNumOfDayOff((Float) result[2]);

        return user;
    }

    //TODO 음수일때 예외처리, 반려일때 날짜 계산 후 차감

    /**
     * A의 연차가 15일 남아있을 때,
     * 3일짜리 연차를 신청 -> 남은 연차 일 수가 12로 저장, dayOff_id = 1
     * 또 2일짜리 연차를 신청 -> 남은 연차 일 수가 10으로 저장, dayOff_id = 2
     * 3일짜리는 반려됨 -> 남은 연차 일 수가 13으로 저장, dayOff_id =1의 numOfDayOff = 13
     * 2일 짜리는 승인됨 -> 남은 연차 일 수가 10 저장, dayOff_id = 2의 numOfDayOff = 10
     * 가장 최근 데이터는 10, 알맞은 데이터는 13
     * <p>
     * 반려했을 때, 대기 상태인 DayOff의 남은 연차를 업데이트
     * <p>
     * A의 연차가 15일 남아있을 때,
     * 3일짜리 연차를 신청 -> 남은 연차 일 수가 12로 저장, dayOff_id = 1
     * 또 2일짜리 연차를 신청 -> 남은 연차 일 수가 10으로 저장, dayOff_id = 2
     * 2일짜리가 반려됨 -> 남은 연차 일 수가 12로 저장, dayOff_id = 1의 남은 연차는 14로 저장됨
     * 3일짜리가 반려됨 -> 남은 연차 일 수가 17로 저장,
     * <p>
     * 이전에 대기상태의 신청이 있다면 먼저 처리하도록 예외처리
     * 반려, 승인 중복 예외처리
     */
    @Transactional
    public void modifyDayOffStatus(Long id, AdminRequest.TreatDayOffDTO request) {
        Float deduction = 0F;
        if (request.getStatus().equals(DayOffStatus.반려)) {
            AdminResponse.DayOffInfoDTO dayOff = new AdminResponse.DayOffInfoDTO(adminRepository.findDayOffById(id));
            if (dayOff.getType().equals(DayOffType.연차)) {
                deduction = Float.valueOf(ChronoUnit.DAYS.between(dayOff.getStartDate(), dayOff.getEndDate()));
            } else if (dayOff.getType().equals(DayOffType.오전반차) || dayOff.getType().equals(DayOffType.오후반차)) {
                deduction = 0.5F;
            }
            modifyWaitingDayOffs(id, deduction);
        }
        adminRepository.updateNumOfDayOffById(id, deduction, request.getStatus(), LocalDate.now());
    }

    @Transactional
    public void modifyWaitingDayOffs(Long id, Float deduction) {
        //유저 아이디로 신청한 내역 중, 대기 상태인 것을 찾아서
        List<Long> waitingDayOffList = findWaitingDayOffList(id);
        waitingDayOffList.remove(id);
        //deduction으로 똑같이 업데이트, 추가하기
        for (Long waiting : waitingDayOffList) {
            adminRepository.updateNumOfDayOffById(waiting, deduction);
        }
    }

    @Transactional
    public List<Long> findWaitingDayOffList(Long id) {
        Long userId = adminRepository.findDayOffById(id).getUser().getId();
        return adminRepository.findDayOffByStatus(userId, DayOffStatus.대기);
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
