package com.example.demo.dto.request;

public class TokenRequest {
    private String token;

    // Getters and setters
    public String getToken() {
        return  token;
    }

    public void setToken(String token) {
        if (token != null && !token.startsWith("Bearer ")) {
            this.token = "Bearer " + token;
        } else {
            this.token = token;
        }
    }

}
