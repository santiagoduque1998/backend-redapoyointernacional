package com.digitalschool.redapoyointernacional.api.controladores;


import com.digitalschool.redapoyointernacional.api.controladores.utilidades.ServicioAutenticacion;
import com.digitalschool.redapoyointernacional.api.validaciones.ValidacionJwt;
import com.digitalschool.redapoyointernacional.api.validaciones.ValidacionLogin;
import com.digitalschool.redapoyointernacional.api.validaciones.ValidacionMensaje;
import com.digitalschool.redapoyointernacional.api.validaciones.ValidacionUsuario;
import com.digitalschool.redapoyointernacional.dominio.servicios.ServicioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;

@RestController
@RequestMapping(ControladorAutenticacion.RUTAPRINCIPAL)
public class ControladorAutenticacion {

    public static final String RUTAPRINCIPAL ="/auth";

    public static final String NUEVOUSUARIO = "/nuevousuario";

    public static final String LOGIN = "/login";

    public static final String REFRESCARSESION = "/refrescarsesion";

    @Autowired
    private ServicioUsuario servicioUsuario;

    @Autowired
    private ServicioAutenticacion servicioAutenticacion;


    @CrossOrigin
    @PostMapping(NUEVOUSUARIO)
    public ResponseEntity<ValidacionMensaje> nuevoUsuario(
            @Valid @RequestBody ValidacionUsuario validacionUsuario,
            BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return new ResponseEntity<>(new ValidacionMensaje("campos mal puestos"), HttpStatus.NOT_ACCEPTABLE);

        }

        if(Boolean.TRUE.equals(servicioUsuario.existsByNombreUsuario(validacionUsuario.getNombreUsuario()))){
            return new ResponseEntity<>(new ValidacionMensaje("El nombre de usuario ya existe"), HttpStatus.BAD_REQUEST);
        }

        try{
            servicioUsuario.crearUsuario(servicioAutenticacion.crearUsuario(validacionUsuario));

            return new ResponseEntity<>(new ValidacionMensaje("Usuario creado correctamente"), HttpStatus.CREATED);
        }catch (Exception e){

            return new ResponseEntity<>(new ValidacionMensaje("Error desconocido"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @CrossOrigin
    @PostMapping
    public ResponseEntity<ValidacionJwt> login(@Valid @RequestBody
    ValidacionLogin validacionLogin,
    BindingResult bindingResult){

    if(bindingResult.hasErrors()){
        return new ResponseEntity<>(new ValidacionJwt("campos mal puestos"), HttpStatus.BAD_REQUEST);
    }

    return new ResponseEntity<>(servicioAutenticacion.autenticarUsuario(validacionLogin), HttpStatus.OK);

    }


    @CrossOrigin
    @PostMapping(REFRESCARSESION)
    public  ResponseEntity<ValidacionJwt> refescarSesion( @Valid @RequestBody ValidacionJwt validacionJwt,
                                                          BindingResult bindingResult) throws ParseException {
        if(bindingResult.hasErrors()){
            return new ResponseEntity<>(new ValidacionJwt("campos mal puestos"), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(servicioAutenticacion.refrescarToken(validacionJwt),HttpStatus.OK);
    }
}
