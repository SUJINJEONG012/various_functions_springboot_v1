package com.various_functions.admin.dto;

public class ApiResponse {
    private boolean success;
    private String message;

    // 기본 생성자
    public ApiResponse() {
    }

    // 성공 여부와 메시지를 받는 생성자
    public ApiResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    // Getter와 Setter 메서드
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
