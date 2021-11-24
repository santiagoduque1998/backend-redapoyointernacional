package com.digitalschool.redapoyointernacional.datos.modelos.entidades;

import com.digitalschool.redapoyointernacional.datos.modelos.enumerados.NombreRol;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class EntidadRol {
    @Id
    @Column(name="rol_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="nombre_rol")
    @Enumerated(EnumType.STRING)
    private NombreRol nombreRol;


    public Integer getId() {
        return id;
    }

    public NombreRol getNombreRol() {
        return nombreRol;
    }



}
