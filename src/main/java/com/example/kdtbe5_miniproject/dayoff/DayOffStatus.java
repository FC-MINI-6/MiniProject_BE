package com.example.kdtbe5_miniproject.dayoff;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DayOffStatus {
    대기(0),
    승인(1),
    반려(2),
    취소(3);

    private final int typeNumber;

    public int getValue() {
        return this.typeNumber;
    }
}
