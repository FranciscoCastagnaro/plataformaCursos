package com.uade.tpo.courseCommerce.repository;

// Imports
import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.uade.tpo.courseCommerce.entity.Category;

// Defino mi interfaz como un repositorio de JPA
// para la entity
// Va a ser mi capa de persistencia (base de datos)
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
 
    @Query(value = "SELECT c FROM Category c WHERE c.description = :description")
    ArrayList<Category> getByDescription(String description);

}
