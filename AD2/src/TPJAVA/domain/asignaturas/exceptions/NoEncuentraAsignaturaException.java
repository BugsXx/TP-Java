package TPJAVA.domain.asignaturas.exceptions;

public class NoEncuentraAsignaturaException extends Exception {
    public NoEncuentraAsignaturaException() {
        super("No se encontro la asignatura\n");
    }
}
