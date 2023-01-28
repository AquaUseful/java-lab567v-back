package org.psu.lab567.pojo;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class LoginRequest {
    @NotBlank(message = "Пустой email")
    private final String email;
    @NotBlank(message = "Пустой пароль")
    private final String password;
}
