package com.uade.tpo.courseCommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uade.tpo.courseCommerce.entity.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    // @Query("SELECT c FROM carts c WHERE c.user_id = userID")
    // Optional<Cart> getByUserID(Long userID);

    Optional<Cart> findByUserId(long id);

}
