package com.example.kdtbe5_miniproject.dayoff;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DayOffType {
    연차(0),
    오전반차(1),
    오후반차(2);

    private final int typeNumber;

    public int getValue() {
        return this.typeNumber;
    }
}
