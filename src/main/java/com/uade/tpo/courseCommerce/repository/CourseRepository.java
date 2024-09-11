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
//Es una interfaz que extiende JpaRepository, lo que proporciona acceso a métodos CRUD estándar y la posibilidad de definir consultas personalizadas.
public interface CourseRepository extends JpaRepository<Course, Long> {
 
    @Query(value = "SELECT c FROM Course c")    //selecciona todas las instancias de curso
    List<Course> getCursos();  //Método que ejecuta la consulta y devuelve una lista de todos los cursos en la base de datos

    @Query(value = "SELECT c FROM Course c WHERE c.description = :description") //busca cursos con descripcion especifica
    List<Course> findByDescripcion(String description);   //Método que devuelve una lista de cursos cuya descripción coincide con el valor proporcionado en el argumento

}
