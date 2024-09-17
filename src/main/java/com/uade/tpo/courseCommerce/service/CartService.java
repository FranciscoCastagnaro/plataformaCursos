package com.uade.tpo.courseCommerce.service;

import java.util.Optional;

import com.uade.tpo.courseCommerce.entity.Cart;

public interface CartService {

    public Optional<Cart> getByUserID (Long userID);

    public Cart createCart(String username);

    public Cart addToCart(String username, String course);

    public Cart deleteFromCart(Long courseId, Long userId);


    public Cart clearCart(Long userId);

}
