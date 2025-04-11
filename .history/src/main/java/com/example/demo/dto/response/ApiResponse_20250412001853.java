package com.example.demo.dto.response;

public class ApiResponse {
    public boolean Success;
    public String message;
    public Object data;
    public ApiResponse(boolean success, String message, Object data) {
        Success = success;
        this.message = message;
        this.data = data;
    }
    public boolean isSuccess() {
        return Success;
    }
    public void setSuccess(boolean success) {
        Success = success;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
    

}
