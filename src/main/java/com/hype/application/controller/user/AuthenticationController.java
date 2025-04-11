package com.hype.application.controller.user;

import com.hype.application.domain.user.LoginResponseDTO;
import com.hype.application.domain.user.User;
import com.hype.application.domain.user.AuthenticationDTO;
import com.hype.application.domain.user.RegisterDTO;
import com.hype.application.infra.TokenService;
import com.hype.application.respositories.user.UserRepository;
import com.hype.application.services.user.userServices;
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
    UserRepository repository;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data ) {
        var UsernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(UsernamePassword);

        var token = tokenService.GenerateToken((User)auth.getPrincipal());

        return ResponseEntity.ok( new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data ){
        userServices service = new userServices();

       if(repository.findByLogin(data.login()) != null ){
            return ResponseEntity.badRequest().build();
        }

       String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
       User newuser = new User ( data.login(), encryptedPassword, data.role());

        repository.save(newuser);

       return ResponseEntity.ok().build();
    }


}
