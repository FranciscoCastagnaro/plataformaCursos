package com.uade.tpo.plataformaCursos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uade.tpo.plataformaCursos.entity.Curso;
import com.uade.tpo.plataformaCursos.exceptions.CursoDuplicadoException;
import com.uade.tpo.plataformaCursos.services.CursoService;


@RestController
@RequestMapping("cursos")
public class CursoController {

    // Inyección de dependencias
    @Autowired
    private CursoService cursoService;

    @GetMapping
    public ResponseEntity<List<Curso>> getCursos () {
        return ResponseEntity.ok(cursoService.getCursos());
    }
    
    @PostMapping("path")
    public ResponseEntity<Object> createCurso (@RequestBody Curso curso) throws CursoDuplicadoException {
        return ResponseEntity.ok(cursoService.createCurso(curso.getDescripcion(),
                                                          curso.getFechaInicio(),
                                                          curso.getCategoria(),
                                                          curso.getMaxVacantes(),
                                                          curso.getProfesor()));
    }
    

}
