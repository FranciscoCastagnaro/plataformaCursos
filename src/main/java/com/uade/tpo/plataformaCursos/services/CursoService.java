package com.uade.tpo.plataformaCursos.services;

import java.util.List;

import com.uade.tpo.plataformaCursos.entity.Curso;

public interface CursoService {

    public List<Curso> getCursos();

    public Curso createCurso(String descripcion,
                             String fechaInicio, 
                             Long categoria, 
                             int maxVacantes,
                             Long profesor);

}
