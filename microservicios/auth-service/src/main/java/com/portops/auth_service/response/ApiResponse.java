package com.portops.auth_service.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {

    private int code;
    private String message;
    private T data;
    private LocalDateTime timestamp;

    public static <T> ApiResponse<T> ok(T data, String message,int code) {
        return new ApiResponse<>(
            code,
            message,
            data,
            LocalDateTime.now()
        );
    }

    public static <T> ApiResponse<T> error(String message,int code) {
        return new ApiResponse<>(
            code,
            message,
            null,
            LocalDateTime.now()
        );
    }
}
