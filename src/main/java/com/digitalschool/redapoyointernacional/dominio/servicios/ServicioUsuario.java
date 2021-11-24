package com.digitalschool.redapoyointernacional.dominio.servicios;


import com.digitalschool.redapoyointernacional.datos.modelos.entidades.EntidadUsuario;
import com.digitalschool.redapoyointernacional.datos.repositorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@Transactional
public class ServicioUsuario {
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    public EntidadUsuario crearUsuario(EntidadUsuario entidadUsuario){
        return usuarioRepositorio.save(entidadUsuario);
    }

    public Optional<EntidadUsuario> getByUserName(String username){
        return usuarioRepositorio.findByNombreUsuario(username);
    }

    public boolean existsByNombreUsuario(String nombreUsuario){
        return usuarioRepositorio.existsByNombreUsuario(nombreUsuario);
    }
}
