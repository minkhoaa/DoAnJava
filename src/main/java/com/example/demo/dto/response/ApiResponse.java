package com.example.demo.dto.response;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ApiResponse {

    public String message;
    public Object data;
 

}
