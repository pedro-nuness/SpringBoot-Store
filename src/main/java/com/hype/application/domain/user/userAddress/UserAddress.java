package com.hype.application.domain.user.userAddress;


import com.hype.application.dto.user.userAddress.UserAddressRequestDTO;
import com.hype.application.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "users_address")
@Entity(name = "users_address")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class UserAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;
    private String cep;
    private String uf;
    private String city;
    private String address;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private User user;

    public UserAddress(UserAddressRequestDTO userAddressRequestDTO){
        this.name = userAddressRequestDTO.name();
        this.cep = userAddressRequestDTO.cep();
        this.address = userAddressRequestDTO.address();
        this.uf = userAddressRequestDTO.uf();
        this.city = userAddressRequestDTO.city();
    }
}
