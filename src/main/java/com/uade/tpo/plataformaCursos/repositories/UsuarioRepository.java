package com.uade.tpo.plataformaCursos.repositories;

// Imports
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uade.tpo.plataformaCursos.entity.Usuario;

// Defino mi interfaz como un repositorio de JPA
// para la entity
// Va a ser mi capa de persistencia (base de datos)
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
 
    Optional<Usuario> findByEmail(String email);

}
