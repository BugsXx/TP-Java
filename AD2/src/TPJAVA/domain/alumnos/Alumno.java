package TPJAVA.domain.alumnos;

import TPJAVA.domain.inscripciones.Inscripcion;

import java.util.LinkedList;
import java.util.List;

public class Alumno extends Persona {
    private List<Inscripcion> inscripciones;
    private String matricula;

    //agregar posibles metodos
    public Alumno(String matricula, String nombreYApellido, String fechaNacimiento){
        super(nombreYApellido,fechaNacimiento);
        this.matricula = matricula;
        inscripciones = new LinkedList<>();
    }

    public String getMatricula(){
        return matricula;
    }

    @Override
    public boolean equals(Object obj) {
        if(this==obj)
            return true;
        else if (obj == null || getClass() != obj.getClass())
            return false;
        else{
            Alumno alumno = (Alumno)obj;
            return this.matricula.equals(alumno.matricula);
        }
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Nombre y Apellido: ").append(getNombreYApellido()).append("\n");
        sb.append("\tMatricula: ").append(matricula).append("\n");
        sb.append("\tFecha de Nacimiento: ").append(getFechaNacimiento()).append("\n");
        return sb.toString();
    }
}
