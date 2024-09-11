//representa la solicitud de autenticacion de un usuario en la aplicacion

package com.uade.tpo.courseCommerce.controller.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data             //genera metodos como getters setters equals hashCode toString etc
@Builder          //permite crear instancias de AuthenticationRequest
@AllArgsConstructor       //genera un constructor con todos los campos de la clase como parametros
@NoArgsConstructor        //genera un constructor sin parametros 

public class AuthenticationRequest {
    private String email;
    String password;
}
