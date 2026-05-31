package TPJAVA.domain;

import TPJAVA.domain.inscripciones.Inscripcion;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Alumno extends Persona{
    private List<Inscripcion> inscripciones;


    private String matricula;

    //agregar posibles metodos


    public String getMatricula(){
        return matricula;
    }

    public boolean equals(Alumno obj) {
        return obj.getMatricula().equals(getMatricula());
    }

    public void muestra(){
        System.out.printf("Nombre y Apellido: %s%n\n\tMatricula: %s%n\n\tFecha de Nacimiento: %s%n\n", getNombreYApellido(), matricula, getFechaNacimiento());

    }


    Alumno(String matricula, String nombreYApellido, String fechaNacimiento){
        super(nombreYApellido,fechaNacimiento);
        this.matricula = matricula;
        inscripciones = new LinkedList<>();

    }
}
