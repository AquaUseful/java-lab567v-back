package org.psu.lab567.service;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.psu.lab567.exception.UserExistsException;
import org.psu.lab567.exception.UserNotFoundException;
import org.psu.lab567.exception.WrongPasswordException;
import org.psu.lab567.model.Role;
import org.psu.lab567.model.User;
import org.psu.lab567.pojo.LoginRequest;
import org.psu.lab567.pojo.LoginResponse;
import org.psu.lab567.pojo.RegisterRequest;
import org.psu.lab567.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.NonNull;

@Service
public class AuthService {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtils jwtUtils;

    public LoginResponse login(@NonNull @Valid LoginRequest request)
            throws UserNotFoundException, WrongPasswordException {
        try {
            final User user = userService.getByEmail(request.getEmail());
            if (user.getPassword().equals(request.getPassword())) {
                final String token = jwtUtils.generate(user);
                return new LoginResponse("Bearer", token);
            } else {
                throw new WrongPasswordException();
            }
        } catch (EntityNotFoundException e) {
            throw new UserNotFoundException();
        }
    }

    public void register(@NonNull @Valid RegisterRequest request) throws UserExistsException {
        if (userService.existsByEmail(request.getEmail())) {
            throw new UserExistsException();
        }
        final User newUser = new User(
                null,
                Role.USER,
                request.getName(),
                request.getEmail(),
                request.getPassword(),
                null);
        userService.save(newUser);
    }
}
