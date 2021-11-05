package com.digitalschool.redapoyointernacional.datos.modelos.entidades;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity

public class EntidadCalificacionCasa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_calificacion_casa")
    private Integer idCalificacionCasa;

    @Column(length = 500)
    private String comentario;

    @Column(name = "calificacion")
    private Integer calidicacion;

    @JoinColumn(name = "fk_casa_afiliada")
    @ManyToOne
    private EntidadCasa casaAfiliada;

    public Integer getIdCalificacionCasa() {
        return idCalificacionCasa;
    }

    public String getComentario() {
        return comentario;
    }

    public Integer getCalidicacion() {
        return calidicacion;
    }

    public EntidadCasa getCasaAfiliada() {
        return casaAfiliada;
    }



}
