package com.uade.tpo.courseCommerce.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uade.tpo.courseCommerce.entity.Category;
import com.uade.tpo.courseCommerce.entity.Course;
import com.uade.tpo.courseCommerce.exception.DuplicatedCourseException;
import com.uade.tpo.courseCommerce.repository.CategoryRepository;
import com.uade.tpo.courseCommerce.repository.CourseRepository;


@Service
public class CourseServiceImpl implements CourseService {
    
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CategoryRepository categoryRespository;

    @Override
    public List<Course> getCursos() {   
        return courseRepository.getCursos();
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public Course createCurso(String description,
                              String startDate, 
                              String category, 
                              int maxSlots,
                              String teacher)
                            throws DuplicatedCourseException 
    {

        List<Course> foundedCourses = courseRepository.findByDescripcion(description);
        if (!foundedCourses.isEmpty()) throw new DuplicatedCourseException();

        ArrayList<Category> foundedCategory = categoryRespository.getByDescription(category);
        Category newCategory;
        if (foundedCategory.isEmpty()) {
            newCategory = new Category(category);
            categoryRespository.save(newCategory);
        } else {
            newCategory = foundedCategory.getFirst();
        }

        var newCurso = new Course(description, startDate, newCategory, maxSlots, teacher);
        courseRepository.save(newCurso);
        return newCurso;

    }

    @Override
    public List<Course> findByDescripcion(String descripcion){
        return courseRepository.findByDescripcion(descripcion);
    }

    @Override
    public Optional<Course> findById(Long id){
        return courseRepository.findById(id);
    }

    @Override
    public Course discountStock(String description) {

        List<Course> foundCourse = findByDescripcion(description);

        Course course;
        if (!foundCourse.isEmpty()) {

            course = foundCourse.getFirst();
            int courseSlots = course.getAvailableSlots();

            if (courseSlots == 0) return null;
            
            course.setAvailableSlots(courseSlots - 1);
            courseRepository.save(course);

        }else{

            course = null;

        }


        return course;

    }


}
