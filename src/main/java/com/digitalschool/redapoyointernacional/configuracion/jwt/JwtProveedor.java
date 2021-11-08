package com.digitalschool.redapoyointernacional.configuracion.jwt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

public class JwtProveedor {
    private static final Logger loggerFilter = LoggerFactory.getLogger(JwtProveedor.class);

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private long jwtExpiration;

    private static final String Roles = "roles";


}
