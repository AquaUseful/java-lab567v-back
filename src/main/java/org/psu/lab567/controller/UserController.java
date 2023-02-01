package org.psu.lab567.controller;

import java.util.Collection;

import org.psu.lab567.auth.JwtAuth;
import org.psu.lab567.model.Role;
import org.psu.lab567.model.User;
import org.psu.lab567.service.AuthService;
import org.psu.lab567.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private AuthService authService;

    @GetMapping(path = "self")
    public ResponseEntity<User> getSelf() {
        final User self = authService.getSelf();
        return ResponseEntity.status(HttpStatus.OK).body(self);
    }

    @GetMapping(path = "self/avatar")
    public ResponseEntity<byte[]> getSelfAvatar() {
        final User self = authService.getSelf();
        if (self.getAvatar() == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {
            final byte[] avatarImage = self.getAvatar().getContent();
            return ResponseEntity.status(HttpStatus.OK).body(avatarImage);
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(path = "")
    public ResponseEntity<Collection<User>> getAll() {
        final Collection<User> users = userService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }
}
