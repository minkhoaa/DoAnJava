package com.example.demo.dto.request;


import jakarta.persistence.Column;
import lombok.*;

import javax.lang.model.element.NestingKind;
import java.util.Set;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class RegisterRequest {
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;

    private String phoneNumber;

}