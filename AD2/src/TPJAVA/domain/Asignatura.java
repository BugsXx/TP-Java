package TPJAVA.domain;


import java.util.ArrayList;
import java.util.List;

public class Asignatura {

    private List<Inscripcion> inscripciones; // inscriptos, pueden ser condicional, oyente o regular
    private String cod;
    private String nombre;
    private int cuatrimestre; // DE 1 A 10
    private boolean promocionable;
    private char tipo;

    private int clasesTotales;

    public int getClasesTotales(){
        return clasesTotales;
    }

    public char getTipo(){
        return tipo;
    }
    public boolean getPromocionable(){
        return promocionable;
    }

    public void cargaAsistencia(Alumno alumno) {
        int i = 0;
        boolean encontrado = false;
        Inscripcion inscripcion = inscripciones.get(i);
        while (i < inscripciones.size() && !inscripcion.getAlumno().equals(alumno)) {
            inscripcion = inscripciones.get(i);
            i++;
        }
        if(i < inscripciones.size()){
            inscripcion.marcaAsistencia();
        }
    }

    Asignatura(){
        inscripciones = new ArrayList<>();
    }
    // agregar metodo abstracto para inscribirse (agregar nodo en la lista dado un alumno como objeto parametro)

    // agregar mas metodos


}
