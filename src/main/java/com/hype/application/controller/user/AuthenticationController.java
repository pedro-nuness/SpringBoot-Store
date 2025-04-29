package com.hype.application.controller.user;

import com.hype.application.dto.auth.*;
import com.hype.application.domain.user.*;
import com.hype.application.dto.auth.request.AuthenticationDTO;
import com.hype.application.dto.auth.request.CheckMailDTO;
import com.hype.application.dto.auth.request.RegisterDTO;
import com.hype.application.exception.EventErrorExpiredTokenException;
import com.hype.application.exception.EventErrorUnauthoriazedException;
import com.hype.application.exception.EventNotFoundException;
import com.hype.application.service.auth.TokenService;
import com.hype.application.infra.Security.TokenType;
import com.hype.application.service.user.UserServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    TokenService tokenService;

    @Autowired
    UserServices userService;

    @PostMapping("/mail")
    public ResponseEntity<MailResponseDTO> CheckMail(@RequestBody @Valid CheckMailDTO checkMailDTO){
        boolean found = userService.getUserRepository().findUserByEmail(checkMailDTO.email()) != null;
        return ResponseEntity.ok(new MailResponseDTO(found));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO>  login(@RequestBody AuthenticationDTO data ) {
        var UsernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = this.authenticationManager.authenticate(UsernamePassword);
        User user = (User)(auth.getPrincipal());
        var accessToken = tokenService.generateAccessToken(user);
        var refreshToken = tokenService.generateRefreshToken(user);
        return ResponseEntity.ok( new LoginResponseDTO(user.getId(), accessToken, refreshToken));
    }

    @PostMapping("/refresh/{refreshToken}")
    public ResponseEntity<LoginResponseDTO> refresh(
            @PathVariable("refreshToken") String refreshToken){
        //return user e-mail
        var tokenUsername = tokenService.ValidateToken(refreshToken, TokenType.REFRESH.getClaimValue());

        if(tokenUsername == null)
            throw new EventErrorExpiredTokenException();

        User user = userService.getUserRepository().findUserByEmail(tokenUsername);

        if(user == null)
            throw new EventErrorExpiredTokenException();

        if(!tokenService.validateAndInvalidateToken(refreshToken)) {
            throw new EventErrorExpiredTokenException();
        }

        var accessToken = tokenService.generateAccessToken(user);
        var newRefreshToken = tokenService.generateRefreshToken(user);

        return ResponseEntity.ok( new LoginResponseDTO(user.getId(),accessToken, newRefreshToken));
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid RegisterDTO data ){
        //Usuario já registrado
        if(userService.getUserRepository().findUserByEmail(data.email()) != null ){
            return ResponseEntity.badRequest().build();
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newuser = new User(data, encryptedPassword, UserRole.USER); // sempre USER
        userService.getUserRepository().save(newuser);
        return ResponseEntity.ok().build();
    }
}
