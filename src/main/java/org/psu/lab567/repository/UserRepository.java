package org.psu.lab567.repository;

import java.util.Optional;

import org.psu.lab567.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lombok.NonNull;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> getByEmail(@NonNull String email);

    boolean existsByEmail(@NonNull String email);
}
