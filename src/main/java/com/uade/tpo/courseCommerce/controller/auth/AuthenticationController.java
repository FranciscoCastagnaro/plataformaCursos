//controlador de autenticacion en la aplicacion

package com.uade.tpo.courseCommerce.controller.auth;            //define el paquete que pertenece este archivo

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uade.tpo.courseCommerce.service.AuthenticationService;

import lombok.RequiredArgsConstructor;

/*ResponseEntity: Una clase para crear respuestas HTTP.
PostMapping, RequestBody, RequestMapping, RestController: Anotaciones de Spring que ayudan a mapear peticiones HTTP a métodos en un controlador.
AuthenticationService: Un servicio que maneja la lógica de autenticación.
RequiredArgsConstructor: Una anotación de Lombok que genera un constructor con todos los atributos final. */

@RestController                //define la clase como un controlador REST, gestiona las solicitudes HTTP y devuelve las respuestas en formato JSON
@RequestMapping("/auth")       //define el prefijo "/auth" para todas las rutas de los metodos
@RequiredArgsConstructor       //genera un constructor con los campos

public class AuthenticationController {

    private final AuthenticationService service;    //constructor

    //metodo register
    @PostMapping("/register")                                  
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(service.register(request));
    }
    
    //metodo authenticate
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(service.authenticate(request));
    }
}