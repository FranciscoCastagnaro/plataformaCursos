package com.uade.tpo.courseCommerce.controller;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uade.tpo.courseCommerce.controller.request.AddToCartRequest;
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
    
    @PostMapping("/add")
    public ResponseEntity<Object> addToCart(@RequestBody AddToCartRequest addToCartRequest) {
        Cart newCart = cartService.addToCart(addToCartRequest.getUsername(), addToCartRequest.getCourse());

        if (Objects.isNull(newCart)) {
            String errorMessage = "User already has this course";
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorMessage);
        }

        return ResponseEntity.ok(newCart);
    }
    
    @DeleteMapping("/remove/{userId}/{courseId})")
    public ResponseEntity<Cart> deleteFromCart(@PathVariable Long userId,@PathVariable Long courseId){
        Cart newCart = cartService.deleteFromCart(courseId,userId);
        return ResponseEntity.ok(newCart);
    }


    @DeleteMapping("/clear/{userId}")
    public ResponseEntity<Cart> clearCart(@PathVariable Long userId){
        Cart newCart = cartService.clearCart(userId);
        return ResponseEntity.ok(newCart);
    }


    @PostMapping("/confirm/{userId}")
    public ResponseEntity<Object> confirmCart(@PathVariable Long userId) {
        Cart cart = cartService.confirmCart(userId);
    
        if (Objects.isNull(cart)) {
            String errorMessage = "Cart not found or access forbidden for user with ID: " + userId;
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorMessage);
        }
    
        return ResponseEntity.ok(cart);
    }

    
}
