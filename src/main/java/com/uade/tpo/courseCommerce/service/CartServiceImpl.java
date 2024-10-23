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
    
        List<Course> foundCourse = courseService.findByDescripcion(course);
        Optional<User> user = authService.getUserByUsername(username);

        User cartUser = user.get();
        Long userID = cartUser.getId();
        
        List<Course> userCourses = cartUser.getCourses();
        for(Course userCourse : userCourses){

            // Busco que el curso no haya sido adquirido ya por el usuario
            if (Objects.equals(userCourse.getDescription(), course)) return null;

        }

        Optional<Cart> cart = getByUserID(userID);

        Cart newCart;
        if (cart.isEmpty()) {
            newCart = createCart(username);
        } else {
            newCart = cart.get();
        }


        List<Course> courses = newCart.getCourses();
        if(!courses.contains(foundCourse.getFirst())){
            courses.add(foundCourse.get(0));
            newCart.setCourses(courses);
            cartRepository.save(newCart);
            return newCart;
        }
        return newCart;

    }

    @Override
    public Cart deleteFromCart(Long courseId, Long userId){
        Optional<Cart> userCart = getByUserID(userId);
        Cart newCart = new Cart();
        if(userCart.isPresent()){
            newCart = userCart.get();
        } else {
            return newCart;
        }
        Optional<Course> course = courseService.findById(userId);
        Course foundCourse;
        System.out.println(2);
        if(course.isPresent()){
            foundCourse = course.get();
        } else {
            return newCart;
        }


        List<Course> cartCourses = newCart.getCourses();
        if(cartCourses.contains(foundCourse)){
            cartCourses.remove(foundCourse);
            newCart.setCourses(cartCourses);
            cartRepository.save(newCart);
            return newCart;
        } else {
            return newCart;
        }
    }

    @Override
    public Cart clearCart(Long userId){
        Optional<Cart> cart = cartRepository.findByUserId(userId);
        List<Course> courses = new ArrayList<>();
        Cart userCart = new Cart(); 
        if(cart.isPresent()){
            userCart = cart.get();
            userCart.setCourses(courses);

        }

        cartRepository.save(userCart);
        return userCart;
    }

    @Override
    public Cart confirmCart(Long userId) {

        Optional<Cart> cart = cartRepository.findByUserId(userId);
        Optional<User> user = userRepository.findById(userId);

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

            clearCart(userId);

        } else {
            return null;
        } 

        return cart.get();
    }
    
    

}
