package org.psu.lab567.service;

import javax.validation.constraints.NotNull;

import org.psu.lab567.model.User;
import org.psu.lab567.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getById(@NotNull Long id) {
        final User user = userRepository.getReferenceById(id);
        return user;
    }

}
