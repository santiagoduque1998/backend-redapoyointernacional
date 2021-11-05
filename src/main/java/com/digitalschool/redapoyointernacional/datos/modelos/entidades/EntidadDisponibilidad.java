package com.digitalschool.redapoyointernacional.datos.modelos.entidades;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder

public class EntidadDisponibilidad {
    @Id
    @Column(name = "id_disponibilidad")

    private Integer idDisponibilidad;

    @JsonFormat(pattern="yyy-mm-dd")
    @Column(name = "fecha_inicio")
    private String fechaInicio;



    @JsonFormat(pattern="yyy-mm-dd")
    @Column(name = "fecha_fin")
    private String fechaFin;

    @JoinColumn(name = "fk_id_casa")
    @ManyToOne
    private EntidadCasa casaReservada;

    @JoinColumn(name = "fk_usuario_reservado")
    @ManyToOne
    private EntidadUsuario usuarioReservado;

    public Integer getIdDisponibilidad() {
        return idDisponibilidad;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public EntidadCasa getCasaReservada() {
        return casaReservada;
    }

    public EntidadUsuario getUsuarioReservado() {
        return usuarioReservado;
    }
}
