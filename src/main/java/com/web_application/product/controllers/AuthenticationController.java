package com.web_application.product.controllers;

import com.web_application.product.entity.User;
import com.web_application.product.dtos.LoginUserDto;
import com.web_application.product.dtos.RegisterUserDto;
import com.web_application.product.responses.LoginResponse;
import com.web_application.product.service.AuthenticationService;
import com.web_application.product.service.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {
    private final JwtService jwtService;
    
    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody RegisterUserDto registerUserDto) {
        User registeredUser = authenticationService.signup(registerUserDto);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        // print user details
        System.out.println(loginUserDto.getPassword());
        User authenticatedUser = authenticationService.authenticate(loginUserDto);
        System.out.println(authenticatedUser.getEmail());
        String jwtToken = jwtService.generateToken(authenticatedUser);
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());
        loginResponse.setRole(authenticatedUser.getRole().getName());

        return ResponseEntity.ok(loginResponse);
    }
}