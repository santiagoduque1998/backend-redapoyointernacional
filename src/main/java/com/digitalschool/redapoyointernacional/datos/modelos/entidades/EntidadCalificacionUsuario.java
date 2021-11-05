package com.digitalschool.redapoyointernacional.datos.modelos.entidades;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity

public class EntidadCalificacionUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_calificacion_usuario")
    private Integer idCalificacionusUario;

    @Column(length = 500)
    private String comentario;

    @Column(name = "calificacion")
    private Integer calificacion;

    @JoinColumn(name = "fk_usuario_afiliado")
    @ManyToOne
    private EntidadUsuario usuarioAfiliado;

    public Integer getIdCalificacionusUario() {
        return idCalificacionusUario;
    }

    public String getComentario() {
        return comentario;
    }

    public Integer getCalificacion() {
        return calificacion;
    }

    public EntidadUsuario getUsuarioAfiliado() {
        return usuarioAfiliado;
    }
}
