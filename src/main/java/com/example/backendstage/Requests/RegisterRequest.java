package com.example.backendstage.Requests;

import com.example.backendstage.Models.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class RegisterRequest {
    @Size(min = 3)
    private String firstName;

    @Size(min = 3)
    private String lastName;

    @Email
    private String email;

    private Role role;

    @Size(min = 6, max = 40)
    private String password;

    private LocalDateTime createdAt;
}
