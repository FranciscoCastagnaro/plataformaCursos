package com.uade.tpo.courseCommerce.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RequestParam;



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
    public ResponseEntity<Cart> addToCart(@RequestBody AddToCartRequest addToCartRequest) {
        Cart newCart = cartService.addToCart(addToCartRequest.getUsername(), addToCartRequest.getCourse());
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


    

    
}
