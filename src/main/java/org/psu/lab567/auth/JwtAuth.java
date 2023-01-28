package org.psu.lab567.auth;

import java.util.Collection;
import java.util.Collections;

import org.hibernate.cfg.NotYetImplementedException;
import org.psu.lab567.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class JwtAuth implements Authentication {

    private boolean isAuthenticated = false;
    private final User user;

    @Override
    public String getName() {
        return this.user.getName();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(this.user.getRole());
    }

    @Override
    public Object getCredentials() {
        throw new NotYetImplementedException();
    }

    @Override
    public Object getDetails() {
        throw new NotYetImplementedException();
    }

    @Override
    public String getPrincipal() {
        return this.user.getEmail();
    }

    @Override
    public boolean isAuthenticated() {
        return this.isAuthenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.isAuthenticated = isAuthenticated;
    }

}
