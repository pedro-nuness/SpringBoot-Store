package com.hype.application.domain.user.useraddress;

public record UserAddressResponseDTO (
        String id,
        String name,
        String cep,
        String uf,
        String city,
        String address
){

   public UserAddressResponseDTO(UserAddress userAddress){
        this(
                userAddress.getId(),
                userAddress.getName(),
                userAddress.getCep(),
                userAddress.getUf(),
                userAddress.getCity(),
                userAddress.getAddress()
        );
    }
}


