package TPJAVA.domain.inscripciones.exceptions;

import TPJAVA.domain.Alumno;

public class NoEncuentraInscripcionException extends RuntimeException {
    public NoEncuentraInscripcionException(Alumno alumno) {

        super("No se encontró el alumno " + " de matricula " + alumno.getMatricula() + " inscripto en la asignatura\n");
    }
}
