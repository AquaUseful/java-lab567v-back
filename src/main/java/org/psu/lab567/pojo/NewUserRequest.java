package org.psu.lab567.pojo;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.psu.lab567.model.Role;
import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class NewUserRequest {
    @NotEmpty(message = "Пустое имя")
    @Pattern(regexp = "^[0-9A-Za-z]{5,20}$", message = "От 5 до 20 символов, только латинские буквы, цифры и _")
    private final String name;

    @NotEmpty(message = "Пустой email")
    @Email(message = "Недопустимый email адрес")
    private final String email;

    @NotEmpty(message = "Пустой пароль")
    @Pattern(regexp = "^(\\S){8,32}$", message = "От 8 до 32 непробельных символов")
    private final String password;

    private final Role role;
    private final MultipartFile avatar;
}
