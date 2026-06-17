package TPJAVA.domain.persistencia.exceptions;

public class NoExisteElArchivoException extends RuntimeException {
    public NoExisteElArchivoException(String message) {
        super(message);
    }
}
