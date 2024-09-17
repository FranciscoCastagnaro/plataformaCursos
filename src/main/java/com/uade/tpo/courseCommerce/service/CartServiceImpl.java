package com.uade.tpo.courseCommerce.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.oauth2.resourceserver.OAuth2ResourceServerSecurityMarker;
import org.springframework.stereotype.Service;

import com.uade.tpo.courseCommerce.entity.Cart;
import com.uade.tpo.courseCommerce.entity.Course;
import com.uade.tpo.courseCommerce.entity.User;
import com.uade.tpo.courseCommerce.repository.CartRepository;
import com.uade.tpo.courseCommerce.repository.UserRepository;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CourseService courseService;

    @Autowired
    private AuthenticationService authService;

    
    @Override
    public Optional<Cart> getByUserID(Long userID) {
        return cartRepository.findByUserId(userID);
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
        
        Optional<Cart> cart = getByUserID(userID); //EN ESTA LINEA ESTA EL ERROR

        System.out.print("Encontro el carrito del usuario");
        Cart newCart;
        if (cart.isEmpty()) {
            newCart = createCart(username);
        } else {
            newCart = cart.get();
        }
        List<Course> courses = newCart.getCourses();
        if(!courses.contains(foundedCourse.getFirst())){
            courses.add(foundedCourse.get(0));
            newCart.setCourses(courses);
            cartRepository.save(newCart);
            return newCart;
        }
        return newCart;

    }

    public Cart deleteFromCart(Long courseId, Long userId){
        Optional<Cart> userCart = getByUserID(userId);
        Cart newCart = new Cart();
        if(userCart.isPresent()){
            newCart = userCart.get();
            System.out.println(1);
        } else {
            return newCart;
        }
        Optional<Course> course = courseService.findById(userId);
        Course foundCourse;
        System.out.println(2);
        if(course.isPresent()){
            foundCourse = course.get();
            System.out.println(3);
        } else {
            return newCart;
        }


        List<Course> cartCourses = newCart.getCourses();
        System.out.println(4);
        if(cartCourses.contains(foundCourse)){
            cartCourses.remove(foundCourse);
            newCart.setCourses(cartCourses);
            cartRepository.save(newCart);
            System.out.println(4);
            return newCart;
        } else {
            return newCart;
        }
    }



    @Override
    public Cart clearCart(Long userId){
        Optional<Cart> cart = cartRepository.findByUserId(userId);
        List<Course> courses = new ArrayList<Course>();
        Cart userCart = new Cart(); 
        if(cart.isPresent()){
            userCart = cart.get();
            userCart.setCourses(courses);

        }

        cartRepository.save(userCart);
        return userCart;
    }
    

}
