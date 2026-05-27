package TPJAVA.domain;

import TPJAVA.domain.inscripciones.Inscripcion;

import java.util.LinkedList;

public class Alumno {
    private LinkedList<Inscripcion> inscripciones;

    private String matricula;

    //agregar posibles metodos


    public String getMatricula(){
        return matricula;
    }

    public boolean equals(Alumno obj) {
        return obj.getMatricula().equals(getMatricula());
    }

    Alumno(String matricula){
        this.matricula = matricula;
        inscripciones = new LinkedList<>();
    }
}
