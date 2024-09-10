package com.uade.tpo.courseCommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uade.tpo.courseCommerce.entity.Category;
import com.uade.tpo.courseCommerce.entity.Course;
import com.uade.tpo.courseCommerce.entity.Teacher;
import com.uade.tpo.courseCommerce.exception.DuplicatedCourseException;
import com.uade.tpo.courseCommerce.repository.CourseRepository;


@Service
public class CourseServiceImpl implements CourseService {
    
    @Autowired
    private CourseRepository courseRepository;

    @Override
    public List<Course> getCursos() {   
        return courseRepository.getCursos();
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public Course createCurso(String description,
                              String startDate, 
                              Category category, 
                              int maxSlots,
                              Teacher teacher)
                            throws DuplicatedCourseException 
    {

        List<Course> foundedCourses = courseRepository.findByDescripcion(description);
        if (!foundedCourses.isEmpty()) throw new DuplicatedCourseException();

        Course newCurso = new Course(description,startDate,category,maxSlots,teacher);
        courseRepository.save(newCurso);
        return newCurso;

    }

    @Override
    public List<Course> findByDescripcion(String descripcion){
        return courseRepository.findByDescripcion(descripcion);
    }


}
