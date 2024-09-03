package com.uade.tpo.courseCommerce.service;

import java.util.List;

import com.uade.tpo.courseCommerce.entity.Category;
import com.uade.tpo.courseCommerce.entity.Course;
import com.uade.tpo.courseCommerce.entity.Teacher;
import com.uade.tpo.courseCommerce.exception.DuplicatedCourseException;


public interface CourseService {

    public List<Course> getCursos();

    public List<Course> findByDescripcion(String descripcion);

    public Course createCurso(String description,
                             String startDate, 
                             Category category, 
                             int maxSlots,
                             Teacher teacher)
                            throws DuplicatedCourseException;

}
