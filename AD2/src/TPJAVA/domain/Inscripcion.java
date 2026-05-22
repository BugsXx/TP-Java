package TPJAVA.domain;
// CLASE BASE, HACER SUBCLASE DE TIPOS DE INSCRIPCION (REGULAR, CONDICIONAL Y OYENTE)

public abstract class Inscripcion{

    private Asignatura asignatura;
    private Alumno alumno;
    private int asistencias;

    public abstract boolean Promociona();
    public  abstract boolean Habilita();

    public Asignatura getAsignatura(){
        return asignatura;
    }
    public int getAsistencias(){
        return asistencias;
    }
    //agregar metodos

}

