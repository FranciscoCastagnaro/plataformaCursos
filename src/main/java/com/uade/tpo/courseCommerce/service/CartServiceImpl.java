package com.uade.tpo.courseCommerce.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uade.tpo.courseCommerce.entity.Cart;
import com.uade.tpo.courseCommerce.entity.Course;
import com.uade.tpo.courseCommerce.entity.User;
import com.uade.tpo.courseCommerce.repository.CartRepository;
import com.uade.tpo.courseCommerce.repository.UserRepository;

@Service
public class CartServiceImpl implements CartService {


    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CourseService courseService;

    @Autowired
    private AuthenticationService authService;

    @Autowired
    private UserRepository userRepository;


    @Override
    public Optional<Cart> getByUserId(Long userID) {
        return cartRepository.findByUserId(userID);
    }


    public Optional<Cart> getByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if(!user.isPresent()) return null;
        return cartRepository.findByUserId(user.get().getId());
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
        List<Course> foundCourse = courseService.findByDescripcion(course);

        if(foundCourse.isEmpty()) return null;
        Course newCourse = foundCourse.getFirst();

        Optional<User> user = authService.getUserByUsername(username);


        User cartUser = user.get();
        
        Long userID = cartUser.getId();
        
        List<Course> userCourses = cartUser.getCourses();
        for(Course userCourse : userCourses){

            // Busco que el curso no haya sido adquirido ya por el usuario
            if (Objects.equals(userCourse.getDescription(), course)) return null;

        }

        Optional<Cart> cart = getByUserId(userID);

        Cart newCart;
        if (cart.isEmpty()) {
            newCart = createCart(username);
        } else {
            newCart = cart.get();
        }


        List<Course> courses = newCart.getCourses();
        if(!courses.contains(newCourse)){
            courses.add(newCourse);
            newCart.setCourses(courses);
            newCart.setTotal(newCart.getTotal() + (int)(newCourse.getPrice() * (1 - (newCourse.getDiscount() / 100.0))));
            cartRepository.save(newCart);
            return newCart;
        }
        return newCart;

    }

    @Override
    public Cart deleteFromCart(String courseDescription, String username){
        Optional<Cart> userCart = getByUsername(username);
        Cart newCart = new Cart();
        if(userCart.isPresent()){
            newCart = userCart.get();
        } else {
            return newCart;
        }
        List<Course> course = courseService.findByDescripcion(courseDescription);
        Course foundCourse;
        if(!course.isEmpty()){
            foundCourse = course.getFirst();
        } else {
            return newCart;
        }


        List<Course> cartCourses = newCart.getCourses();
        if(cartCourses.contains(foundCourse)){
            cartCourses.remove(foundCourse);
            newCart.setCourses(cartCourses);
            cartRepository.save(newCart);
            newCart.setTotal(newCart.getTotal() - (int)(foundCourse.getPrice() * (1 - (foundCourse.getDiscount() / 100.0))));
            cartRepository.save(newCart);
            return newCart;
        } else {
            return newCart;
        }
    }

    @Override
    public Cart clearCart(String username){
        Optional<Cart> cart = getByUsername(username);
        List<Course> courses = new ArrayList<>();
        Cart userCart = new Cart(); 
        if(cart.isPresent()){
            userCart = cart.get();
            userCart.setCourses(courses);

        }
        userCart.setTotal(0);
        cartRepository.save(userCart);
        return userCart;
    }

    @Override
    public Cart confirmCart(String username) {

        Optional<Cart> cart = getByUsername(username);
        Optional<User> user = userRepository.findByUsername(username);

        if (!user.isPresent()) return null;
        User confirmedUser = user.get();

        if(cart.isPresent()){

            List<Course> cartCourses = cart.get().getCourses();

            for (Course cartCourse : cartCourses) {
                
                Course course = courseService.discountStock(cartCourse.getDescription());

                // Ocurrió un error al descontar stock
                if (Objects.isNull(course)) return null;


                List<Course> userCourses = confirmedUser.getCourses();
                userCourses.add(course);
                confirmedUser.setCourses(userCourses);
                userRepository.save(confirmedUser);


            }

            clearCart(username);

        } else {
            return null;
        } 

        return cart.get();
    }
    
    

}
