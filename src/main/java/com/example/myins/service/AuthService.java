package com.example.myins.service;

import com.example.myins.model.dto.UserRegisterFormDto;
import com.example.myins.model.entity.UserEntity;
import com.example.myins.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    //    private final EmailService emailService;
    private final String defaultAdminPass;

    @Autowired
    public AuthService(UserRepo userRepo,
                       PasswordEncoder passwordEncoder,
                       @Value("${myIns.admin.defaultpass}") String defaultAdminPass) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.defaultAdminPass = defaultAdminPass;
    }

    public void registerUser(UserRegisterFormDto registrationDTO) {

        UserEntity userEntity = new UserEntity()
                .setEmail(registrationDTO.getEmail())
                .setPhone(registrationDTO.getPhone())
                .setPassword(passwordEncoder.encode(registrationDTO.getPassword()));

        userRepo.save(userEntity);

        //   emailService.sendRegistrationEmail(userEntity.getEmail(),
        //            userEntity.getFirstName() + " " + userEntity.getLastName());
    }
}
