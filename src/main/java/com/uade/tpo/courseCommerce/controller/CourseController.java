//gestiona las solicitudes relacionadas con los cursos

package com.uade.tpo.courseCommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uade.tpo.courseCommerce.entity.Course;
import com.uade.tpo.courseCommerce.exception.DuplicatedCourseException;
import com.uade.tpo.courseCommerce.service.CourseService;


@RestController                //define la clase como un controlador REST, gestiona las solicitudes HTTP y devuelve las respuestas en formato JSON
@RequestMapping("courses")     //define el prefijo "courses" para todas las rutas de los metodos

public class CourseController {

    // Inyección de dependencias
    @Autowired            
    private CourseService courseService;        //Esta anotación permite la inyección automática del servicio CourseService

    @GetMapping          // Este método gestiona las solicitudes HTTP GET dirigidas a /courses
    //El método devuelve una lista de objetos Course envuelta en un ResponseEntity, que permite controlar el estado HTTP de la respuesta.
    public ResponseEntity<List<Course>> getCursos () {
        return ResponseEntity.ok(courseService.getCursos());
    }
    
    @PostMapping        //Este método maneja las solicitudes HTTP POST dirigidas a /courses
    /*@RequestBody Course course: Indica que el método espera recibir un cuerpo de solicitud en formato JSON, que se deserializa en un objeto Course.
    throws DuplicatedCourseException: Indica que el método puede lanzar una excepción DuplicatedCourseException si se intenta crear un curso duplicado.
    courseService.createCurso(...): Llama al servicio para crear un nuevo curso con los detalles proporcionados en el objeto Course. La respuesta se envuelve en un ResponseEntity */
    public ResponseEntity<Object> createCurso (@RequestBody Course course) throws DuplicatedCourseException {
        return ResponseEntity.ok(courseService.createCurso(course.getDescription(),
                                                           course.getStartDate(),
                                                           course.getCategory(),
                                                           course.getMaxSlots(),
                                                           course.getTeacher()));
    }
    

}
