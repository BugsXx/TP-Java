package TPJAVA.domain;

import java.util.LinkedList;

public class Universidad {
    private LinkedList<Asignatura> asignaturas;


    Universidad(){
        asignaturas = new LinkedList<>();
    }
    public void agregarAsignatura(String cod, String nombre, boolean promocionable, int cuatrimestre, char tipo, int clasesTotales){
        Asignatura asignatura = new Asignatura(cod,nombre,promocionable,cuatrimestre,tipo, clasesTotales);
        asignaturas.add(asignatura);
    }
}
