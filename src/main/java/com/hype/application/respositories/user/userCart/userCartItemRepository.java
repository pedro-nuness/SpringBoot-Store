package com.hype.application.respositories.user.userCart;

import com.hype.application.domain.user.userCart.UserCartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface userCartItemRepository extends JpaRepository<UserCartItem, String> {

}