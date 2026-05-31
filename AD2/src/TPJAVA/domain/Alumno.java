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

    public String muestra(){
        StringBuilder sb = new StringBuilder();
        sb.append("Nombre y Apellido: %s%n").append(getNombreYApellido());
        sb.append("\tMatricula: ");
        sb.append(matricula).append("\n");
        sb.append("\tFecha de Nacimiento: ").append(getFechaNacimiento()).append("\n");
        return sb.toString();
    }


    Alumno(String matricula, String nombreYApellido, String fechaNacimiento){
        super(nombreYApellido,fechaNacimiento);
        this.matricula = matricula;
        inscripciones = new LinkedList<>();

    }
}
