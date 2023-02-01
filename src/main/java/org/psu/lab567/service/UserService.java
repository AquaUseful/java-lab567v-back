package org.psu.lab567.service;

import java.util.Collection;

import javax.persistence.EntityNotFoundException;

import org.psu.lab567.model.User;
import org.psu.lab567.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.NonNull;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

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

}
