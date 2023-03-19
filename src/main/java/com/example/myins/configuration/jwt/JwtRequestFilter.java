package com.example.myins.configuration.jwt;

import com.example.myins.model.entity.UserEntity;
import com.example.myins.service.ApplicationUserDetailsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Slf4j
@Order
public class JwtRequestFilter extends OncePerRequestFilter {

    private final ApplicationUserDetailsService userService;

    private final JwtUtils jwtUtils;

    public JwtRequestFilter(ApplicationUserDetailsService userService, JwtUtils jwtUtils) {
        this.userService = userService;
        this.jwtUtils = jwtUtils;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authorizationHeader = request.getHeader("Authorization");
        String email = null;
        String jwtToken = null;
        if (authorizationHeader != null) {
            if (authorizationHeader.startsWith("Bearer ")) {
                jwtToken = authorizationHeader.substring(7);
                try {
                    email = jwtUtils.getUsernameFromToken(jwtToken);
                } catch (IllegalArgumentException ex) {
                    log.error("Unable to get JWT token.");
                    return;
                } catch (ExpiredJwtException ex) {
                    log.error("JWT token has expired.");
                    return;
                }
            } else {
                log.error("JWT token does not begin with 'Bearer ' prefix.");
                return;
            }
        }

//        if (email != null) {
//            UserEntity user = userService.getUserByEmail(email);
//            if (jwtUtils.validateToken(jwtToken, user)) {
//                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
//                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//            } else {
//                throw new BadCredentialsException("JWT token has expired.");
//            }
//        }
        filterChain.doFilter(request, response);
    }

    public String convertObjectToJson(Object object) throws JsonProcessingException {
        if (object == null) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }
}
