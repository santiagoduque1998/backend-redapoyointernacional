package com.digitalschool.redapoyointernacional.datos.repositorio;


import com.digitalschool.redapoyointernacional.datos.modelos.entidades.EntidadRol;
import com.digitalschool.redapoyointernacional.datos.modelos.enumerados.NombreRol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolRepositorio extends JpaRepository<EntidadRol, Long> {

    Optional<EntidadRol> findByNombreRol(NombreRol nombreRol);

    boolean existsByNombreRol(NombreRol nombreRol);

}
