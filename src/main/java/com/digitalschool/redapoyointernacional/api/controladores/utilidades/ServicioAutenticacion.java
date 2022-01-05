package com.digitalschool.redapoyointernacional.api.controladores.utilidades;

import com.digitalschool.redapoyointernacional.api.validaciones.ValidacionJwt;
import com.digitalschool.redapoyointernacional.api.validaciones.ValidacionLogin;
import com.digitalschool.redapoyointernacional.api.validaciones.ValidacionUsuario;
import com.digitalschool.redapoyointernacional.configuracion.jwt.JwtProveedor;
import com.digitalschool.redapoyointernacional.datos.modelos.entidades.EntidadRol;
import com.digitalschool.redapoyointernacional.datos.modelos.entidades.EntidadUsuario;
import com.digitalschool.redapoyointernacional.datos.modelos.enumerados.NombreRol;
import com.digitalschool.redapoyointernacional.dominio.servicios.ServicioRol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.HashSet;
import java.util.Set;

@Service
public class ServicioAutenticacion {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtProveedor jwtProveedor;

    @Autowired
    private ServicioRol servicioRol;

    public EntidadUsuario crearUsuario(ValidacionUsuario validacionUsuario) {
        Set<EntidadRol> rolesUsuario = new HashSet<>();

        servicioRol.getByNombreRol(NombreRol.VIAJERO).ifPresent(rolesUsuario::add);

        if(validacionUsuario.getRoles().contains("anfitrion")){
            servicioRol.getByNombreRol(NombreRol.ANFITRION).ifPresent(rolesUsuario::add);
        }

        return EntidadUsuario.builder().idUsuario(null).nombreUsuario(validacionUsuario.getNombreUsuario())
                .clave(passwordEncoder.encode(validacionUsuario.getContraseña()))
                .pais(validacionUsuario.getPais())
                .ciudad(validacionUsuario.getCiudad())
                .nombreCompleto(validacionUsuario.getNombreCompleto())
                .entidadRol(rolesUsuario).build();
    }


    public ValidacionJwt autenticarUsuario(ValidacionLogin login){


        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                login.getNombreUsuario(),login.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new ValidacionJwt(jwtProveedor.generarToken(authentication));
    }

    public ValidacionJwt refrescarToken(ValidacionJwt jwt) throws ParseException {

        return new ValidacionJwt(jwtProveedor.refrescarToken(jwt));
    }

}
