package com.uade.tpo.plataformaCursos.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uade.tpo.plataformaCursos.entity.Curso;
import com.uade.tpo.plataformaCursos.entity.Profesor;
import com.uade.tpo.plataformaCursos.repositories.CursoRepository;

@Service
public class CursoServiceImpl implements CursoService {
    
    @Autowired
    private CursoRepository cursoRepository;

    @Override
    public List<Curso> getCursos() {   
        return cursoRepository.getCursos();
    }


    @Override
    public Curso createCurso(String descripcion,
                             String fechaInicio, 
                             Long categoria, 
                             int maxVacantes,
                             Profesor profesor) 
    {

        Curso newCurso = new Curso(descripcion, fechaInicio, categoria, maxVacantes, profesor);
        cursoRepository.save(newCurso);
        return newCurso;

    }

    


}
