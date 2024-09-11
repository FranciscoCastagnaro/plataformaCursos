package com.uade.tpo.courseCommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.uade.tpo.courseCommerce.entity.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    @Query(value = "SELECT c FROM Cart c WHERE c.user = :userID")
    Optional<Cart> getByUserID(Long userID);


}
