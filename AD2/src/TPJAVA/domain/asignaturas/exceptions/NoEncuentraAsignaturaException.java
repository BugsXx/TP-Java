package TPJAVA.domain.asignaturas.exceptions;

public class NoEncuentraAsignaturaException extends RuntimeException {
    public NoEncuentraAsignaturaException() {
        super("No se encontro la asignatura\n");
    }
}
