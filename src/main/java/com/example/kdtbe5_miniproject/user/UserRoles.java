package com.example.kdtbe5_miniproject.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserRoles {
    일반(0),
    관리자(1);

    private final int typeNumber;
}
