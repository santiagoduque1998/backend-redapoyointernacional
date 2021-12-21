package com.digitalschool.redapoyointernacional.dominio.servicios;

import com.digitalschool.redapoyointernacional.datos.modelos.UsuarioPrincipal;
import com.digitalschool.redapoyointernacional.datos.modelos.entidades.EntidadUsuario;
import com.digitalschool.redapoyointernacional.datos.repositorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServicioDetalleUsuario implements UserDetailsService {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<EntidadUsuario> usuario = usuarioRepositorio.findByNombreUsuario(userName);

        return usuario.map(UsuarioPrincipal::build).orElse(null);

    }
}
