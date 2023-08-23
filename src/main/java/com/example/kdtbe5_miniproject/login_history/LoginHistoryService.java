package com.example.kdtbe5_miniproject.login_history;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class LoginHistoryService {

    private final LoginHistoryRepository loginHistoryRepository;

    @Transactional
    public void save(LoginHistoryRequest.LoginHistoryDTO loginHistoryDTO) {
        loginHistoryRepository.save(loginHistoryDTO.toEntity());
    }
}
