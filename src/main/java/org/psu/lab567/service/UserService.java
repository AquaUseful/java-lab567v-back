package org.psu.lab567.service;

import javax.persistence.EntityNotFoundException;

import org.psu.lab567.model.User;
import org.psu.lab567.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.NonNull;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getById(@NonNull Long id) {
        final User user = userRepository.getReferenceById(id);
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

}
