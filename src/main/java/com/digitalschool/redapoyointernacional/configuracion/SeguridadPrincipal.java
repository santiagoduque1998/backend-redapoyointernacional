package com.digitalschool.redapoyointernacional.configuracion;


import com.digitalschool.redapoyointernacional.configuracion.jwt.JwtFiltro;
import com.digitalschool.redapoyointernacional.configuracion.jwt.JwtPuntoEntrada;
import com.digitalschool.redapoyointernacional.dominio.servicios.ServicioDetalleUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SeguridadPrincipal extends WebSecurityConfigurerAdapter {

    @Autowired
    private ServicioDetalleUsuario servicioDetalleUsuario;

    @Autowired
    private JwtPuntoEntrada jwtPuntoEntrada;

    @Bean
    public JwtFiltro jwtFiltro() {
        return new JwtFiltro();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}


