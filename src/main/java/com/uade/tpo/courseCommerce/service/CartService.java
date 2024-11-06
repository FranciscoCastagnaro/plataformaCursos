package com.uade.tpo.courseCommerce.service;

import java.util.Optional;

import com.uade.tpo.courseCommerce.entity.Cart;

public interface CartService {

    public Optional<Cart> getByUserId (Long userID);

    public Optional<Cart> getByUsername (String username);

    public Cart createCart(String username);

    public Cart addToCart(String username, String course);

    public Cart deleteFromCart(String courseDescription, String username);

    public Cart clearCart(String username);

    public Cart confirmCart(String username);

}
