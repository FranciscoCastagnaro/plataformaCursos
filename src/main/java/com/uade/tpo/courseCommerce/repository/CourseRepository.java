package com.uade.tpo.courseCommerce.repository;

// Imports
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.uade.tpo.courseCommerce.entity.Course;

// Defino mi interfaz como un repositorio de JPA
// para la entity
// Va a ser mi capa de persistencia (base de datos)
@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
 
    @Query(value = "SELECT c FROM Course c")
    List<Course> getCursos();

    @Query(value = "SELECT c FROM Course c WHERE c.description = :description")
    List<Course> findByDescripcion(String description);

}
