package com.digitalschool.redapoyointernacional.datos.modelos.entidades;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class EntidadUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id_usuario")
    private Integer idUsuario;


    @Column(name = "nombre_usuario", unique = true)
    private String nombreUsuario;


    private String clave;
    @Column(name = "nombre_completo")
    private String nombreCompleto;

    private String ciudad;
    private String pais;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_rol",
               joinColumns = @JoinColumn(name = "usuario_id"),
               inverseJoinColumns =  @JoinColumn(name = "rol_id"))
    private Set<EntidadRol> entidadRol = new HashSet<EntidadRol>();

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }


    public Integer getIdUsuario() {
        return idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getClave() {
        return clave;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getPais() {
        return pais;
    }

    public Set<EntidadRol> getEntidadRol() {
        return entidadRol;
    }




}
