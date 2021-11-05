package com.digitalschool.redapoyointernacional.datos.modelos.entidades;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder

public class EntidadCasa {
    @Id
    @Column(name="id_casa")
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer idCasa;

    private String direccion;

    private String telefono;

    private String pais;

    private String estado;

    private String[] fotos;

    @JoinColumn(name = "fk_propietario")
    @ManyToOne
    private EntidadUsuario usuarioEntity;
    ///////////////////////////////////////////////////////////////////
    //Creacion de la entidad para la tabla casa

    public Integer getIdCasa() {
        return idCasa;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getPais() {
        return pais;
    }

    public String getEstado() {
        return estado;
    }

    public String[] getFotos() {
        return fotos;
    }

    public EntidadUsuario getUsuarioEntity() {
        return usuarioEntity;
    }
}
