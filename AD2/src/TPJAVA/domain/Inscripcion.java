package TPJAVA.domain;
// CLASE BASE, HACER SUBCLASE DE TIPOS DE INSCRIPCION (REGULAR, CONDICIONAL Y OYENTE)

public abstract class Inscripcion{
    private Alumno alumno;
    private int asistencias;

    public abstract boolean Promociona();
}

