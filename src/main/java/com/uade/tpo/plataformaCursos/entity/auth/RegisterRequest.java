package com.uade.tpo.plataformaCursos.entity.auth;

import com.uade.tpo.plataformaCursos.entity.Rol;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String usuario;
    private String email;
    private String password;
    private Rol rol;

}
