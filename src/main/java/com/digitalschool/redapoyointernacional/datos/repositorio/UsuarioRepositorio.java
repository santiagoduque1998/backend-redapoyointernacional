package com.digitalschool.redapoyointernacional.datos.repositorio;


import com.digitalschool.redapoyointernacional.datos.modelos.entidades.EntidadUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepositorio extends JpaRepository<EntidadUsuario, Long> {
    Optional<EntidadUsuario> findByNombreUsuario(String nombreUsuario);

    boolean existsByNombreUsuario(String nombreUsuario);
}
