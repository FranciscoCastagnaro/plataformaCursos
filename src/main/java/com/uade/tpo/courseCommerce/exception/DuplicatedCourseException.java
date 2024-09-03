package com.uade.tpo.courseCommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "The course is already created")
public class DuplicatedCourseException extends Exception {

}
