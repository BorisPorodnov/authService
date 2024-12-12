package net.porodnov.springsecurityexample.controllers;

import net.porodnov.springsecurityexample.config.jwt.JwtUtils;
import net.porodnov.springsecurityexample.pojo.JwtResponse;
import net.porodnov.springsecurityexample.pojo.LoginRequest;
import net.porodnov.springsecurityexample.pojo.SignUpRequest;
import net.porodnov.springsecurityexample.services.RegistrationService;
import net.porodnov.springsecurityexample.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    RegistrationService registrationService;

    @Autowired
    JwtUtils jwtUtils;


    @PostMapping("/sign-in")
    public ResponseEntity<?> authUser(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        loginRequest.getLogin(),
                        loginRequest.getPassword())
                );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getLogin(),
                userDetails.getEmail()));
    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> registerUser(@RequestBody SignUpRequest signupRequest) {
        return ResponseEntity.ok(registrationService.createUser(signupRequest));
    }
}