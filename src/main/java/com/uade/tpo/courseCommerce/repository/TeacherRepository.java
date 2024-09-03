package com.uade.tpo.courseCommerce.repository;

// Imports
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uade.tpo.courseCommerce.entity.Teacher;

// Defino mi interfaz como un repositorio de JPA
// para la entity
// Va a ser mi capa de persistencia (base de datos)
@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
 

}
