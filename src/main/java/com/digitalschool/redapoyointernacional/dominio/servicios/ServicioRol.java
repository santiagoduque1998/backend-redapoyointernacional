package com.digitalschool.redapoyointernacional.dominio.servicios;


import com.digitalschool.redapoyointernacional.datos.modelos.entidades.EntidadRol;
import com.digitalschool.redapoyointernacional.datos.repositorio.RolRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class ServicioRol {

    @Autowired
    private RolRepositorio rolRepositorio;

    public Optional<EntidadRol> getByNombreRol(String nombreRol){

        return rolRepositorio.findByNombreRol(nombreRol);
    }

    public EntidadRol crearRol(EntidadRol rol){
        return rolRepositorio.save(rol);
    }
}
