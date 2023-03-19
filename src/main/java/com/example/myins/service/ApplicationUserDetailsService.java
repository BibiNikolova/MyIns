package com.example.myins.service;

import com.example.myins.model.entity.Role;
import com.example.myins.model.entity.UserEntity;
import com.example.myins.repository.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public class ApplicationUserDetailsService implements UserDetailsService {

  private final UserRepo userRepo;

  public ApplicationUserDetailsService(UserRepo userRepo) {
    this.userRepo = userRepo;
  }

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    return
        userRepo.
            findByEmail(email)
                .map(this::map)
                .orElseThrow(() -> new UsernameNotFoundException("User with name " + email + " not found!"));
  }

  private UserDetails map(UserEntity userEntity) {
    return new User (
        userEntity.getEmail(),
        userEntity.getPassword(),
        extractAuthorities(userEntity)
    );
  }

  private List<GrantedAuthority> extractAuthorities(UserEntity userEntity) {
    return userEntity
            .getRoles()
            .stream()
            .map(this::mapRole)
            .toList();
  }

  private GrantedAuthority mapRole(Role role) {
    return new SimpleGrantedAuthority("ROLE_" + role.getRole().name());
  }
}
