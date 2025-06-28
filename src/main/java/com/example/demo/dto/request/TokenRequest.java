package com.example.demo.dto.request;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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
