package com.digitalschool.redapoyointernacional.configuracion;


import com.digitalschool.redapoyointernacional.configuracion.jwt.JwtFiltro;
import com.digitalschool.redapoyointernacional.configuracion.jwt.JwtPuntoEntrada;
import com.digitalschool.redapoyointernacional.dominio.servicios.ServicioDetalleUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

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

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{

        auth.userDetailsService(servicioDetalleUsuario).passwordEncoder(passwordEncoder());

    }

    @Bean
    public AuthenticationManager authenticationMannagerBean() throws Exception{
        return super.authenticationManagerBean();

    }


    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers("/auth/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(jwtPuntoEntrada)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtFiltro(), UsernamePasswordAuthenticationFilter.class);

    }
}


