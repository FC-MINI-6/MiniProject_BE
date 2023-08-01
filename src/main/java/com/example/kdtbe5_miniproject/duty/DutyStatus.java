package com.example.kdtbe5_miniproject.duty;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DutyStatus {
    대기(0),
    승인(1),
    반려(2);

    private final int typeStatus;
}
