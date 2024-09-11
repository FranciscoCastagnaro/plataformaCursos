package com.uade.tpo.courseCommerce.service;

import java.util.Optional;

import com.uade.tpo.courseCommerce.entity.Cart;

public interface CartService {

    public Optional<Cart> getByUserID (int userID);

}
