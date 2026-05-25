package TPJAVA.domain;


import java.util.ArrayList;
import java.util.LinkedList;
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
        Inscripcion inscripcion = inscripciones.get(i);
        while (i < inscripciones.size() && !inscripcion.getAlumno().equals(alumno)) {
            inscripcion = inscripciones.get(i);
            i++;
        }
        if(i < inscripciones.size()){
            inscripcion.marcaAsistencia();
        }
    }

    Asignatura(String cod, String nombre, boolean promocionable, int cuatrimestre, char tipo, int clasesTotales){
        inscripciones = new LinkedList<>();
        this.cod = cod;
        this.nombre = nombre;
        this.promocionable = promocionable;
        this.cuatrimestre = cuatrimestre;
        this.tipo = tipo;
        this.clasesTotales = clasesTotales;
    }
    // agregar metodo abstracto para inscribirse (agregar nodo en la lista dado un alumno como objeto parametro)

    // agregar mas metodos


}
