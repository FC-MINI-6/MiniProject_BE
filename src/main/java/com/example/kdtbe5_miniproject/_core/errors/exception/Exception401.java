package com.example.kdtbe5_miniproject._core.errors.exception;


import com.example.kdtbe5_miniproject._core.util.ApiUtils;
import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
public class Exception401 extends RuntimeException {
    public Exception401(String message) {
        super(message);
    }

    public ApiUtils.ApiMessageResult<?> body() {
        return ApiUtils.error(getMessage());
    }

    public HttpStatus status() {
        return HttpStatus.UNAUTHORIZED;
    }
}