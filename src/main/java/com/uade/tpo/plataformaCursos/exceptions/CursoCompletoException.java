package com.uade.tpo.plataformaCursos.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "La capacidad del curso ya está completa. Por favor, elija otro curso.")
public class CursoCompletoException extends Exception {

}
