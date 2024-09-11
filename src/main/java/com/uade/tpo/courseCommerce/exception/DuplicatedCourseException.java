package com.uade.tpo.courseCommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//Esta anotación se utiliza para especificar el estado HTTP y el mensaje que se debe devolver cuando se lanza esta excepción
@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "The course is already created")
public class DuplicatedCourseException extends Exception {      //define excepcion

}
