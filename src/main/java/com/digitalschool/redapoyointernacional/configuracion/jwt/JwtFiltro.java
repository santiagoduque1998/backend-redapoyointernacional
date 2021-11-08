package com.digitalschool.redapoyointernacional.configuracion.jwt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

public class JwtFiltro extends OncePerRequestFilter {

    private static final Logger loggerFilter = LoggerFactory.getLogger(JwtFiltro.class);
}
