package com.uade.tpo.courseCommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uade.tpo.courseCommerce.entity.Course;
import com.uade.tpo.courseCommerce.entity.requestEntity.RequestCourse;
import com.uade.tpo.courseCommerce.entity.requestEntity.RequestCourseDelete;
import com.uade.tpo.courseCommerce.entity.requestEntity.RequestCourseDiscount;
import com.uade.tpo.courseCommerce.entity.requestEntity.RequestCourseEdit;
import com.uade.tpo.courseCommerce.exception.DuplicatedCourseException;
import com.uade.tpo.courseCommerce.service.CourseService;


@CrossOrigin(origins = "http://localhost:5173")

@RestController
@RequestMapping("courses")
public class CourseController {

    // Inyección de dependencias
    @Autowired
    private CourseService courseService;

    @GetMapping
    public ResponseEntity<List<Course>> getCursos() {
        return ResponseEntity.ok(courseService.getCursos());
    }

    @PostMapping
    public ResponseEntity<Object> createCurso(@RequestBody RequestCourse course) throws DuplicatedCourseException {
        return ResponseEntity.ok(courseService.createCurso(course.getDescription(),
                course.getLongDescription(),
                course.getStartDate(),
                course.getCategory(),
                course.getMaxSlots(),
                course.getTeacher()));
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteCurso(@RequestBody RequestCourseDelete course) {
        return ResponseEntity.ok(courseService.deleteCurso(course.getDescription()));
    }

    @PutMapping
    public ResponseEntity<Object> editCurso(@RequestBody RequestCourseEdit course) {
        return ResponseEntity.ok(courseService.editCourse(course));
    }

    @PutMapping("/discount")
    public ResponseEntity<Object> setDiscount(@RequestBody RequestCourseDiscount course) {
        return ResponseEntity.ok(courseService.setDiscount(course.getDescription(), course.getDiscount()));
    }

}
