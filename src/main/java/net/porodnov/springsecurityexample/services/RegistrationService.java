package net.porodnov.springsecurityexample.services;

import net.porodnov.springsecurityexample.models.User;
import net.porodnov.springsecurityexample.dto.MessageResponse;
import net.porodnov.springsecurityexample.dto.SignUpRequest;
import net.porodnov.springsecurityexample.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class RegistrationService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;


    public MessageResponse createUser(SignUpRequest signupRequest) {
        if (isUserExist(signupRequest)) {
            return new MessageResponse("Error: Username is exist");
        }
        if (isEmailExist(signupRequest)) {
            return new MessageResponse("Error: Email is exist");
        }

        userRepository.save(User.builder().login(signupRequest.getLogin())
                .password(passwordEncoder.encode(signupRequest.getPassword()))
                .email(signupRequest.getEmail())
                .date(new Date()).build());
        return new MessageResponse("User  created");
    }

    private boolean isUserExist(SignUpRequest signupRequest) {
        return userRepository.existsByLogin(signupRequest.getLogin());
    }

    private boolean isEmailExist(SignUpRequest signupRequest) {
        return userRepository.existsByEmail(signupRequest.getEmail());
    }
}
