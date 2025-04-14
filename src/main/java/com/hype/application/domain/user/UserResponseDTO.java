package com.hype.application.domain.user;

import com.hype.application.domain.user.userAddress.UserAddressResponseDTO;

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