package com.digitalschool.redapoyointernacional.configuracion.jwt;

import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtPuntoEntrada implements AuthenticationEntryPoint {

    private static final Logger logger = LoggerFactory.getLogger(JwtPuntoEntrada.class);
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException e) throws IOException {
        logger.error("error en el metodo commence");
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "UNAUTHORIZED");

    }

}
