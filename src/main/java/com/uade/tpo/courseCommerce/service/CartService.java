package com.uade.tpo.courseCommerce.service;

import java.util.List;
import java.util.Optional;

import com.uade.tpo.courseCommerce.entity.Cart;
import com.uade.tpo.courseCommerce.entity.Course;

public interface CartService {

    public Optional<Cart> getByUserID (Long userID);

    public Cart createCart(String username);

    public Cart addToCart(String username, String course);

    public Cart deleteFromCart(Long courseId, Long userId);

    public Cart clearCart(Long userId);

    public Cart confirmCart(Long userId);

    public List<Course> getUserCourses(Long userID);

}
