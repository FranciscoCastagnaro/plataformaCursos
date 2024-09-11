package com.uade.tpo.courseCommerce.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uade.tpo.courseCommerce.entity.Cart;
import com.uade.tpo.courseCommerce.service.CartService;


@RestController
@RequestMapping("cart")
public class CartController {

    @Autowired
    CartService cartService;

    @GetMapping("/{userID}")
    public ResponseEntity<Cart> getCartByUserID(@PathVariable Long userID) {
        Optional<Cart> result = cartService.getByUserID(userID);
        if (result.isPresent()) return ResponseEntity.ok(result.get());
        return ResponseEntity.noContent().build();
    }
    
    @PostMapping("/add/{course}")
    public ResponseEntity<Cart> addToCart(@PathVariable String course, @RequestBody String username) {
        Cart newCart = cartService.addToCart(username, course);
        return ResponseEntity.ok(newCart);
    }
    
    
}
