package com.uade.tpo.courseCommerce.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uade.tpo.courseCommerce.entity.Cart;
import com.uade.tpo.courseCommerce.entity.Course;
import com.uade.tpo.courseCommerce.entity.User;
import com.uade.tpo.courseCommerce.repository.CartRepository;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CourseService courseService;

    @Autowired
    private AuthenticationService authService;

    
    @Override
    public Optional<Cart> getByUserID(Long userID) {
        return cartRepository.getByUserID(userID);
    }

    //creamos el carrito en caso de que no lo tenga
    @Override
    public Cart createCart(String username) {
        Optional<User> user = authService.getUserByUsername(username);
        User cartUser = user.get();
        Cart newCart = new Cart(cartUser);
        cartRepository.save(newCart);
        return newCart; 
    }
    
    //añadimos el curso al carrito
    @Override
    public Cart addToCart(String username, String course) {
    
        List<Course> foundedCourse = courseService.findByDescripcion(course);
        
        Optional<User> user = authService.getUserByUsername(username);
        
        User cartUser = user.get();
        Long userID = cartUser.getId();

        System.out.println(cartUser);
        System.out.println(userID);

        Optional<Cart> cart = getByUserID(userID);

        Cart newCart;
        if (cart.isEmpty()) {
            newCart = createCart(username);
        } else {
            newCart = cart.get();
        }

        List<Course> courses = newCart.getCourses();
        if (courses == null) {
            courses = new ArrayList<>();
        }
        courses.add(foundedCourse.get(0));
        newCart.setCourses(courses);

        cartRepository.save(newCart);

        return newCart;

    }
    

}
