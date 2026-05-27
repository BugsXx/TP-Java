package TPJAVA.domain;

import TPJAVA.domain.inscripciones.Inscripcion;

import java.util.Iterator;
import java.util.LinkedList;

public class Alumno {
    private LinkedList<Inscripcion> inscripciones;



    private String nombreYApellido;

    private String fechaNacimiento;
    private String matricula;

    //agregar posibles metodos


    public String getMatricula(){
        return matricula;
    }

    public boolean equals(Alumno obj) {
        return obj.getMatricula().equals(getMatricula());
    }

    public void muestra(){
        System.out.printf("%s%n%s%n%s%n", nombreYApellido, matricula, fechaNacimiento);

    }


    Alumno(String matricula, String nombreYApellido, String fechaNacimiento){
        this.matricula = matricula;
        inscripciones = new LinkedList<>();
        this.nombreYApellido = nombreYApellido;
        this.fechaNacimiento = fechaNacimiento;
    }
}
