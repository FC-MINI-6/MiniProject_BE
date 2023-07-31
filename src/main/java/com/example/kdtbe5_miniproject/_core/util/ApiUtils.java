package com.example.kdtbe5_miniproject._core.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

// 공통 응답 DTO
public class ApiUtils {
    public static <T> ApiDataResult<T> success(T data) {
        return new ApiDataResult<>(true, data);
    }

    public static <T> ApiMessageResult<T> success(String message) {
        return new ApiMessageResult<>(true, message);
    }

    public static ApiMessageResult<?> error(String message) {
        return new ApiMessageResult<>(false, message);
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class ApiDataResult<T> {
        private final boolean success;
        private final T data;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class ApiMessageResult<T> {
        private final boolean success;
        private final String message;
    }
}
