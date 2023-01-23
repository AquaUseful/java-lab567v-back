package org.psu.lab567.model;

import org.springframework.security.core.GrantedAuthority;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Role implements GrantedAuthority {

    ADMIN("ADMIN"),
    EDITOR("EDITOR"),
    USER("USER");

    private final String roleName;

    @Override
    public String getAuthority() {
        return roleName;
    }

}
