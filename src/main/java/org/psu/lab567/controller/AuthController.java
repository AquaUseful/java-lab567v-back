package org.psu.lab567.controller;

import javax.validation.Valid;

import org.psu.lab567.auth.JwtAuth;
import org.psu.lab567.exception.UserExistsException;
import org.psu.lab567.exception.UserNotFoundException;
import org.psu.lab567.exception.WrongPasswordException;
import org.psu.lab567.pojo.LoginRequest;
import org.psu.lab567.pojo.LoginResponse;
import org.psu.lab567.pojo.RegisterRequest;
import org.psu.lab567.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.NonNull;

@RestController
@RequestMapping(path = "auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping(path = "login")
    public ResponseEntity<LoginResponse> login(@NonNull @RequestBody @Valid LoginRequest request)
            throws UserNotFoundException, WrongPasswordException {
        final LoginResponse response = authService.login(request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping(path = "register")
    public ResponseEntity<Void> register(@NonNull @RequestBody @Valid RegisterRequest request)
            throws UserExistsException {
        authService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @GetMapping(path = "validate")
    public ResponseEntity<Void> validate() {
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
