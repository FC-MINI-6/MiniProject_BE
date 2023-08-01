package com.example.kdtbe5_miniproject.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserPosition {
    사원(0),
    주임(1),
    대리(2),
    과장(3),
    차장(4),
    부장(5);

    private final int typeNumber;
}
