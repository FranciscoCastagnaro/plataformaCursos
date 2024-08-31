package com.uade.tpo.plataformaCursos.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "El curso que se intenta agregar esta duplicado")
public class CursoDuplicadoException extends Exception {

}
