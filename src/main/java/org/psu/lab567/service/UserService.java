package org.psu.lab567.service;

import java.util.Collection;

import javax.persistence.EntityNotFoundException;

import org.psu.lab567.model.BinFile;
import org.psu.lab567.model.User;
import org.psu.lab567.pojo.NewUserRequest;
import org.psu.lab567.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.NonNull;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BinFileService binFileService;

    public User getById(@NonNull Long id) {
        final User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        return user;
    }

    public User getByEmail(@NonNull String email) {
        final User user = userRepository.getByEmail(email).orElseThrow(
                () -> new EntityNotFoundException());
        return user;
    }

    public boolean existsByEmail(@NonNull String email) {
        return userRepository.existsByEmail(email);
    }

    public void save(@NonNull User user) {
        userRepository.save(user);
    }

    public Collection<User> getAll() {
        final Collection<User> users = userRepository.findAll();
        return users;
    }

    public void setAvatar(@NonNull User user, @NonNull BinFile avatar) {
        user.setAvatar(avatar);
        userRepository.save(user);
    }

    public void incrementLoginCount(@NonNull User user) {
        user.setLoginCount(user.getLoginCount() + 1);
        userRepository.save(user);
    }

    public void add(@NonNull NewUserRequest request) {
        final User newUser = new User(request.getName(),
                request.getEmail(),
                request.getPassword(),
                request.getRole());
        userRepository.save(newUser);
    }

    public void deleteById(@NonNull Long id) {
        userRepository.deleteById(id);
    }
}
