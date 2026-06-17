package TPJAVA.domain.alumnos;

import TPJAVA.domain.asignaturas.exceptions.YaInscriptoAAsignaturaException;
import TPJAVA.domain.inscripciones.Inscripcion;

import java.util.LinkedList;
import java.util.List;

public class Alumno extends Persona implements Comparable<Alumno> {
    private List<Inscripcion> inscripciones;
    private String matricula;

    public Alumno(String matricula, String nombreYApellido, String fechaNacimiento){
        super(nombreYApellido,fechaNacimiento);
        this.matricula = matricula;
        inscripciones = new LinkedList<>();
    }

    public void agregaInscripcion(Inscripcion inscripcion) throws YaInscriptoAAsignaturaException {
        if(! inscripciones.contains(inscripcion))
            inscripciones.add(inscripcion);
        else throw new YaInscriptoAAsignaturaException("El alumno ya se encuentra inscripto en la asignatura");
    }

    public String getMatricula(){
        return matricula;
    }




    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Nombre y Apellido: ").append(getNombreYApellido()).append("\n");
        sb.append("\tMatricula: ").append(matricula).append("\n");
        sb.append("\tFecha de Nacimiento: ").append(getFechaNacimiento()).append("\n");
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Alumno)
            return ((Alumno) obj).getNombreYApellido().equals(this.getNombreYApellido()) && ((Alumno) obj).getFechaNacimiento().equals(this.getFechaNacimiento());
        else  return false;
    }

    @Override
    public int compareTo(Alumno o) {
        return o.getNombreYApellido().compareTo(this.getNombreYApellido());
    }
}
