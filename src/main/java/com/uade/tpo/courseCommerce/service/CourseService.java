package com.uade.tpo.courseCommerce.service;

import java.util.List;
import java.util.Optional;

import com.uade.tpo.courseCommerce.entity.Course;
import com.uade.tpo.courseCommerce.exception.DuplicatedCourseException;


public interface CourseService {

    public List<Course> getCursos();

    public List<Course> findByDescripcion(String descripcion);

    public Course createCurso(String description,
                             String startDate, 
                             String category, 
                             int maxSlots,
                             String teacher)
                            throws DuplicatedCourseException;

    public Optional<Course> findById(Long id);

    public Course discountStock(String description);

}
