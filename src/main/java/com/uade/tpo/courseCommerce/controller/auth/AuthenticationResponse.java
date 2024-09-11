//representa la respuesta que se le envia al cliente 

package com.uade.tpo.courseCommerce.controller.auth;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data             //genera metodos como getters setters equals hashCode toString etc
@Builder          //permite crear instancias de AuthenticationRequest
@AllArgsConstructor        //genera un constructor con todos los campos de la clase como parametros
@NoArgsConstructor         //genera un constructor sin parametros
public class AuthenticationResponse {
    @JsonProperty("access_token")      //es para que en el JSON salga ese nombre "access_token" en vez de el predeterminado
    private String accessToken;       //representa el token de acceso que se devuelve al cliente tras una autenticacion exitosa
}
