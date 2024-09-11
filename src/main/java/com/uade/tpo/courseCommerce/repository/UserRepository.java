package com.uade.tpo.courseCommerce.repository;

// Imports
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uade.tpo.courseCommerce.entity.User;



// Defino mi interfaz como un repositorio de JPA
// para la entity
// Va a ser mi capa de persistencia (base de datos)
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    Optional<User> findByEmail(String email);
    
    Optional<User> findByUsername(String username);

}
