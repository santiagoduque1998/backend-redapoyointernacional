package com.digitalschool.redapoyointernacional.configuracion.jwt;

import com.digitalschool.redapoyointernacional.dominio.servicios.ServicioDetalleUsuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtFiltro extends OncePerRequestFilter {

    private static final Logger loggerFilter = LoggerFactory.getLogger(JwtFiltro.class);

    @Autowired
    private JwtProveedor jwtProveedor;

    @Autowired
    private ServicioDetalleUsuario servicioDetalleUsuario;

    private static final String BEARER =  "Bearer";

    private static final String AUTORIZACION = "Authorization";

    private String getToken(HttpServletRequest req){
        String cabecera = req.getHeader(AUTORIZACION);
        if(cabecera != null && cabecera.startsWith(BEARER)){
            return cabecera.replace(BEARER, "");
        }
        return null;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res,
                                    FilterChain filterchain) throws ServletException, IOException{
        try{
            String token = getToken(req);

            if(token != null && jwtProveedor.validarToken(token)){

                String username = jwtProveedor.obtenerUsuarioMedianteToken(token);

                UserDetails userDetails = servicioDetalleUsuario.loadUserByUsername(username);

                UsernamePasswordAuthenticationToken upAuth =

                new UsernamePasswordAuthenticationToken(userDetails,  null,
                        userDetails.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(upAuth);

            }


        }catch(Exception e){
            loggerFilter.error("falló el método dofilter");

        }

        filterchain.doFilter(req, res);
    }


}
