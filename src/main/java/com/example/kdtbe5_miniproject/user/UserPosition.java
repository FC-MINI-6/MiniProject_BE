package com.example.kdtbe5_miniproject.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserPosition {
    사원(0),
    대리(1),
    과장(2),
    차장(3),
    부장(4);

    private final int typeNumber;
}
