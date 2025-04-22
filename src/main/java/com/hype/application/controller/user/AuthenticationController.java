package com.hype.application.controller.user;

import com.hype.application.dto.user.AuthenticationDTO;
import com.hype.application.dto.user.LoginResponseDTO;
import com.hype.application.domain.user.*;
import com.hype.application.infra.Security.TokenService;
import com.hype.application.service.user.UserServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    TokenService tokenService;

    @Autowired
    UserServices userService;


    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO>  login(@RequestBody @Valid AuthenticationDTO data ) {
        var UsernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(UsernamePassword);
        var token = tokenService.GenerateToken((User)auth.getPrincipal());
        return ResponseEntity.ok( new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid AuthenticationDTO data ){

        //Usuario já registrado
        if(userService.getUserRepository().findByLogin(data.login()) != null ){
            return ResponseEntity.badRequest().build();
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newuser = new User(data.login(), encryptedPassword, UserRole.USER); // sempre USER
        userService.getUserRepository().save(newuser);
        return ResponseEntity.ok().build();
    }
}
