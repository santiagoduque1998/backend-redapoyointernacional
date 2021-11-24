package com.digitalschool.redapoyointernacional.configuracion.jwt;

import com.digitalschool.redapoyointernacional.api.validaciones.ValidacionJwt;
import com.digitalschool.redapoyointernacional.datos.modelos.UsuarioPrincipal;
import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.JWTParser;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class JwtProveedor {
    private static final Logger logger = LoggerFactory.getLogger(JwtProveedor.class);

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private long jwtExpiration;

    private static final String ROLES = "roles";

    public String generarToken (Authentication autenticacion){

        UsuarioPrincipal usuarioPrincipal = (UsuarioPrincipal) autenticacion.getPrincipal();
        List<String> roles = usuarioPrincipal.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return Jwts.builder().setSubject(usuarioPrincipal.getNombreUsuario())
                .setIssuedAt(new Date())
                .claim(ROLES, roles)
                .setExpiration(new Date(new Date().getTime() + jwtExpiration))
                .signWith(SignatureAlgorithm.HS512, jwtSecret.getBytes())
                .compact();
    }

    public boolean validarToken (String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret.getBytes()).parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException e) {
            logger.error("Token mal formado");
        } catch (UnsupportedJwtException e) {
            logger.error("unsupported token");
        } catch (ExpiredJwtException e) {
            logger.error("Token Expirado");
        } catch (IllegalArgumentException e){
            logger.error("Token Ilegal");
        } catch (SignatureException e) {
            logger.error("Fallo en la firma");
        }
        return false;

    }

    public String refrescarToken(ValidacionJwt validacionjwt) throws ParseException {
        try{
            Jwts.parser().setSigningKey(jwtSecret.getBytes())
                    .parseClaimsJws(validacionjwt.getToken());
        }catch(ExpiredJwtException e){
            JWT jwt = JWTParser.parse(validacionjwt.getToken() );

            JWTClaimsSet claims = jwt.getJWTClaimsSet();

            String nombreUsuario = claims.getSubject();

            List<String> roles = (List<String>)claims.getClaim(ROLES);

            return Jwts.builder()
                    .setSubject(nombreUsuario)
                    .claim(ROLES, roles)
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(new Date().getTime() + jwtExpiration))
                    .signWith(SignatureAlgorithm.HS512, jwtSecret.getBytes())
                    .compact();

        }
        return null;
    }

    public String obtenerUsuarioMedianteToken(String token){
        return Jwts.parser()
                .setSigningKey(jwtSecret.getBytes())
                .parseClaimsJws(token)
                .getBody().getSubject();

    }


}
