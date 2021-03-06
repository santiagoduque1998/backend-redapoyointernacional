package com.digitalschool.redapoyointernacional.dominio.utilidades;

import com.digitalschool.redapoyointernacional.datos.modelos.entidades.EntidadRol;
import com.digitalschool.redapoyointernacional.datos.modelos.enumerados.NombreRol;
import com.digitalschool.redapoyointernacional.dominio.servicios.ServicioRol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CrearRol implements CommandLineRunner {

    @Autowired
    ServicioRol servicioRol;

    @Override
    public void run (String...args){

        if(!servicioRol.existePorNombreRol(NombreRol.ANFITRION) && !servicioRol.existePorNombreRol(NombreRol.VIAJERO)){

            EntidadRol rolViajero = new EntidadRol(null, NombreRol.VIAJERO);
            EntidadRol rolAnfitrion = new EntidadRol(null,NombreRol.ANFITRION);
            servicioRol.crearRol(rolViajero);
            servicioRol.crearRol(rolAnfitrion);
        }




    }
}
