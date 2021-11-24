package com.digitalschool.redapoyointernacional.api.validaciones;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NonNull;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@Builder
public class ValidacionJwt {

    @NonNull
    @NotBlank
    private String token;

    public String getToken(){
        return token;
    }
}
