package com.uade.tpo.courseCommerce.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uade.tpo.courseCommerce.entity.Cart;
import com.uade.tpo.courseCommerce.repository.CartRepository;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    
    @Override
    public Optional<Cart> getByUserID(int userID) {
        return cartRepository.getByUserID(userID);
    }
    

}
