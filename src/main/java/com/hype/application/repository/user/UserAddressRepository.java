package com.hype.application.repository.user;


import com.hype.application.domain.user.userAddress.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAddressRepository extends JpaRepository<UserAddress, String>  {

}
