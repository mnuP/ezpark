package com.dailycodework.ezpark.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class LoginRequest {
    @NotBlank
    private String email;
    @NotBlank
    private String contraseña;
}