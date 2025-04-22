package com.hype.application.controller.user;

import com.hype.application.domain.user.User;
import com.hype.application.dto.user.UserResponseDTO;
import com.hype.application.domain.user.userAddress.UserAddress;
import com.hype.application.dto.user.userAddress.UserAddressRequestDTO;
import com.hype.application.exception.EventErrorUnauthoriazedException;
import com.hype.application.exception.EventNotFoundException;
import com.hype.application.repository.user.UserAddressRepository;
import com.hype.application.service.user.UserServices;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {


    @Autowired
    UserAddressRepository userAddressRepository;

    @Autowired
    UserServices userService;

    @GetMapping
    public List<UserResponseDTO> getAllUsers() {
        if(userService.CurrentUserNotAdmin()) {
            throw new EventErrorUnauthoriazedException();
        }

        List<UserResponseDTO> userList = this.userService.getUserRepository().findAll().stream().map(UserResponseDTO::new).toList();

        return ResponseEntity.ok(userList).getBody();
    }

    @GetMapping("{id}")
    public ResponseEntity<UserResponseDTO> GetUser(@PathVariable String id){
        User user = userService.AuthenticateUser(id, false);

        return ResponseEntity.ok(new UserResponseDTO(user));
    }


    @DeleteMapping("{id}")
    private ResponseEntity<String> DeleteUser(@PathVariable("id") @NotEmpty String id){
        User user = userService.AuthenticateUser(id, true);

        userService.getUserRepository().delete(user);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/addAddress/{id}")
    public ResponseEntity<UserResponseDTO> addAddress(@PathVariable("id") @NotEmpty String id, @RequestBody @Valid UserAddressRequestDTO userAddressRequestDTO) {
        User user = userService.AuthenticateUser(id, false);

        UserAddress newAddress = new UserAddress(userAddressRequestDTO);
        newAddress.setUser(user);
        user.getAddresses().add(newAddress);

        userService.getUserRepository().save(user);

        return ResponseEntity.ok(new UserResponseDTO(user));
    }

    @DeleteMapping ("/deleteAddress/{id}/{addressId}")
    public ResponseEntity <UserResponseDTO> deleteAddress(@PathVariable("id") @NotBlank  String id, @PathVariable("addressId") @NotBlank String addressId) {

        User user = userService.AuthenticateUser(id, false);

        Optional<UserAddress> addressOptional = user.getAddresses()
                .stream()
                .filter(addr -> addr.getId().equals(addressId))
                .findFirst();

        if (addressOptional.isEmpty()) {
            throw new EventNotFoundException("Address not found");
        }

        user.getAddresses().remove(addressOptional.get());

        userService.getUserRepository().save(user);
        return ResponseEntity.ok(new UserResponseDTO(user));
    }

    @PutMapping ("/editAddress/{id}/{addressId}")
    public ResponseEntity <UserResponseDTO> UpdateAddress(@PathVariable("id") @NotBlank  String id,
                                                          @PathVariable("addressId") @NotBlank String addressId,
                                                          @RequestBody @Valid UserAddressRequestDTO userAddressRequestDTO) {

        User user = userService.AuthenticateUser(id, false);

        Optional<UserAddress> addressOptional = user.getAddresses()
                .stream()
                .filter(addr -> addr.getId().equals(addressId))
                .findFirst();

        if (addressOptional.isEmpty()) {
            throw new EventNotFoundException("Address not found");
        }

        UserAddress address = addressOptional.get();

        address.setName(userAddressRequestDTO.name());
        address.setUf(userAddressRequestDTO.uf());
        address.setCity(userAddressRequestDTO.city());
        address.setCep(userAddressRequestDTO.cep());
        address.setAddress(userAddressRequestDTO.address());

        userService.getUserRepository().save(user);
        return ResponseEntity.ok(new UserResponseDTO(user));
    }



}
