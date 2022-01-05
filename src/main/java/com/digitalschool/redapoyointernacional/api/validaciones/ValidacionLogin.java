package com.digitalschool.redapoyointernacional.api.validaciones;


import lombok.AllArgsConstructor;
import lombok.Builder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Builder
public class ValidacionLogin {

    @NotNull
    @NotBlank
    private String nombreUsuario;

    @NotNull
    @NotBlank
    private String password;

    public String getNombreUsuario(){
        return nombreUsuario;
    }

    public String getPassword() {
        return password;
    }


}

