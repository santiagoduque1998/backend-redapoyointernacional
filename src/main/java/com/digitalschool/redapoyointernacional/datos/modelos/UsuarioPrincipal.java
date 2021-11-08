package com.digitalschool.redapoyointernacional.datos.modelos;

import com.digitalschool.redapoyointernacional.datos.modelos.entidades.EntidadUsuario;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
@AllArgsConstructor
public class UsuarioPrincipal implements UserDetails {

    private String nombreUsuario;
    private String nombreCompleto;
    private String clave;
    private String ciudad;
    private String pais;
    private Collection<? extends GrantedAuthority> autoridades;

    public static UsuarioPrincipal build(EntidadUsuario entidadUsuario) {

        List<GrantedAuthority> autoridades = entidadUsuario.getEntidadRol().stream()
                .map(entidadRol -> new SimpleGrantedAuthority(entidadRol.getNombreRol().name()))
                .collect(Collectors.toList());
        return new UsuarioPrincipal(entidadUsuario.getNombreUsuario(), entidadUsuario.getClave(),
                entidadUsuario.getNombreCompleto(), entidadUsuario.getCiudad(), entidadUsuario.getPais(), autoridades);
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return autoridades;
    }

    @Override
    public String getPassword() {
        return clave;
    }

    @Override
    public String getUsername() {
        return nombreUsuario;
    }

    @Override
    public boolean isAccountNonLocked(){
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired(){
        return true;
    }

    @Override
    public boolean isEnabled(){
        return true;
    }

    @Override
    public boolean isAccountNonExpired(){
        return true;
    }
    public String getNombreUsuario() {
        return nombreUsuario;
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





}
