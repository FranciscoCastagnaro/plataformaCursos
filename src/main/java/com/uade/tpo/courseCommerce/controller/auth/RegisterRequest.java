//representa la solicitud de registro de un nuevo usuario en la aplicacion

package com.uade.tpo.courseCommerce.controller.auth;


import com.uade.tpo.courseCommerce.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data          //genera metodos como getters setters equals hashCode toString etc
@Builder       //permite crear instancias de AuthenticationRequest
@AllArgsConstructor        //genera un constructor con todos los campos de la clase como parametros
@NoArgsConstructor         //genera un constructor sin parametros

public class RegisterRequest {
    private String username;
    private String email;
    private String password;
    private Role role;
}
