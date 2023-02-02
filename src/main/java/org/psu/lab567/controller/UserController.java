package org.psu.lab567.controller;

import java.io.IOException;
import java.util.Collection;

import javax.validation.Valid;

import org.psu.lab567.model.BinFile;
import org.psu.lab567.model.User;
import org.psu.lab567.pojo.NewUserRequest;
import org.psu.lab567.service.AuthService;
import org.psu.lab567.service.BinFileService;
import org.psu.lab567.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.NonNull;

@RestController
@RequestMapping(path = "user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private AuthService authService;
    @Autowired
    private BinFileService binFileService;

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

    @PostMapping(path = "self/avatar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> postSelfAvatar(@RequestPart("file") MultipartFile request) throws IOException {
        final User self = authService.getSelf();
        if (self.getAvatar() == null) {
            final BinFile avatar = binFileService.createFromMultipart(request);
            userService.setAvatar(self, avatar);
        } else {
            final BinFile avatar = binFileService.updateFromMultipart(self.getAvatar(), request);
            userService.setAvatar(self, avatar);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(path = "")
    public ResponseEntity<Collection<User>> getAll() {
        final Collection<User> users = userService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(path = "")
    public ResponseEntity<Void> addUser(@NonNull @RequestBody @Valid NewUserRequest request) {
        userService.add(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> deleteUser(@NonNull @PathVariable("id") Long id) {
        userService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
