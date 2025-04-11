package com.hype.application.controller.user;

import com.hype.application.domain.user.User;
import com.hype.application.domain.user.UserResponseDTO;
import com.hype.application.domain.user.useraddress.UserAddress;
import com.hype.application.domain.user.useraddress.UserAddressRequestDTO;
import com.hype.application.exceptions.EventNotFoundException;
import com.hype.application.respositories.user.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserRepository userRepository;


    @GetMapping
    public List<UserResponseDTO> getAllUsers() {
       List<UserResponseDTO> userList = this.userRepository.findAll().stream().map(UserResponseDTO::new).toList();

        return ResponseEntity.ok(userList).getBody();
    }



    @PostMapping("/addAddress/{id}")
    public ResponseEntity<UserAddress> addAddress(@PathVariable("id") String id, @RequestBody @Valid UserAddressRequestDTO userAddressRequestDTO) {

        Optional<User> optionalUser = userRepository.findById(id);

        if(optionalUser.isEmpty()) {
            throw new EventNotFoundException();
        }

        UserAddress newAddress = new UserAddress(userAddressRequestDTO);
        User user = optionalUser.get();
        newAddress.setUser(user);
        user.getAddresses().add(newAddress);


        userRepository.save(user);

        return ResponseEntity.ok().build();
    }
}
