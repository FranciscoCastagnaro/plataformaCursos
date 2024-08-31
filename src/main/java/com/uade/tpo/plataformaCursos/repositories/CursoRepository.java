package com.uade.tpo.plataformaCursos.repositories;

// Imports
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.uade.tpo.plataformaCursos.entity.Curso;

// Defino mi interfaz como un repositorio de JPA
// para la entity
// Va a ser mi capa de persistencia (base de datos)
@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
 
    @Query(value = "SELECT c FROM cursos c")
    List<Curso> getCursos();

    @Query(value = "SELECT C FROM cursos c WHERE descripcion = $1")
    List<Curso> findByDescripcion(String descripcion);

}
