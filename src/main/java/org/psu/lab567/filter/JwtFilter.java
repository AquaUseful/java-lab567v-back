package org.psu.lab567.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.psu.lab567.auth.JwtAuth;
import org.psu.lab567.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.Claims;
import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
public class JwtFilter extends GenericFilterBean {

    private static final String authorizationHeaderName = "Authorization";

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        final String token = extractToken((HttpServletRequest) request);
        if (token != null && jwtUtils.validate(token)) {
            final Claims claims = jwtUtils.extractClaims(token);
            final JwtAuth auth = jwtUtils.generateAuth(claims);
            auth.setAuthenticated(true);
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        chain.doFilter(request, response);
    }

    private static String extractToken(HttpServletRequest request) {
        final String tokenStart = "Bearer ";
        String authorisationHeader = request.getHeader(JwtFilter.authorizationHeaderName);
        if (authorisationHeader != null && authorisationHeader.startsWith(tokenStart)) {
            return authorisationHeader.substring(tokenStart.length());
        }
        return null;
    }
}
