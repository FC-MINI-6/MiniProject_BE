package com.example.kdtbe5_miniproject._core.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

// 공통 응답 DTO
public class ApiUtils {
    public static <T> ApiResult<T> success(T data, HttpStatus status) {
        return new ApiResult<>(status.value(), true, data, null);
    }

    public static ApiResult<?> error(String message, HttpStatus status) {
        return new ApiResult<>(status.value(), false, null, new ApiError(message, status.value()));
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class ApiResult<T> {
        private int code;
        private final boolean success;
        private final T data;
        private final ApiError error;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class ApiError {
        private final String message;
        private final int status;
    }
}
