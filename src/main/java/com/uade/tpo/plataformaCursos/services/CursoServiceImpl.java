package com.uade.tpo.plataformaCursos.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uade.tpo.plataformaCursos.entity.Curso;
import com.uade.tpo.plataformaCursos.entity.Profesor;
import com.uade.tpo.plataformaCursos.exceptions.CursoCompletoException;
import com.uade.tpo.plataformaCursos.exceptions.CursoDuplicadoException;
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
    @Transactional(rollbackFor = Throwable.class)
    public Curso createCurso(String descripcion,
                             String fechaInicio, 
                             Long categoria, 
                             int maxVacantes,
                             Profesor profesor)
                            throws CursoDuplicadoException
    {

        List<Curso> cursosEncontrados = cursoRepository.findByDescripcion(descripcion);
        if (!cursosEncontrados.isEmpty()) throw new CursoDuplicadoException();

        List<Curso> cursosDisponibles = cursoRepository.findByCapacity(maxVacantes);
        if (!cursosDisponibles.isEmpty()) try {
            throw new CursoCompletoException();
        } catch (CursoCompletoException ex) {
        }

        Curso newCurso = new Curso(descripcion, fechaInicio, categoria, maxVacantes, profesor);
        cursoRepository.save(newCurso);
        return newCurso;

    }

}
