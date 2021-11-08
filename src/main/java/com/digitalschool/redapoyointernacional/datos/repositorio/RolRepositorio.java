package com.digitalschool.redapoyointernacional.datos.repositorio;


import com.digitalschool.redapoyointernacional.datos.modelos.entidades.EntidadRol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolRepositorio extends JpaRepository<EntidadRol, Long> {

    Optional<EntidadRol> findByNombreRol(String nombreRol);


}
