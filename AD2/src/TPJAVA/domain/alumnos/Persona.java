package TPJAVA.domain.alumnos;

import java.io.Serializable;

public class Persona implements Serializable {
    private String nombreYApellido;
    private String fechaNacimiento;

    public Persona(String nombre, String fecha){
        setFechaNacimiento(fecha);
        setNombreYApellido(nombre);
    }

    public void setNombreYApellido(String nombreYApellido) {
        this.nombreYApellido = nombreYApellido;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getNombreYApellido() {
        return nombreYApellido;
    }
}
