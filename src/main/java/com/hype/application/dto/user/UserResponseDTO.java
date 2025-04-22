package com.hype.application.dto.user;

import com.hype.application.domain.user.User;
import com.hype.application.domain.user.UserRole;
import com.hype.application.dto.user.userAddress.UserAddressResponseDTO;

import java.util.List;


public record UserResponseDTO
        (String id,
         String login,
         UserRole role,
         List<UserAddressResponseDTO> addresses
        ) {

    public UserResponseDTO(User user){
        this(
                user.getId(),
                user.getLogin(),
                user.getRole(),
                user.getAddresses().stream().map(UserAddressResponseDTO::new).toList()
        );
    }
}