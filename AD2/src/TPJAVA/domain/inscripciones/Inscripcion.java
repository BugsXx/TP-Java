package TPJAVA.domain.inscripciones;
// CLASE BASE, HACER SUBCLASE DE TIPOS DE INSCRIPCION (REGULAR, CONDICIONAL Y OYENTE)

import TPJAVA.domain.Alumno;
import TPJAVA.domain.asignaturas.Asignatura;
import TPJAVA.domain.asignaturas.Clase;

import java.util.LinkedList;

public abstract class Inscripcion{

    private Asignatura asignatura;
    private Alumno alumno;

    private LinkedList<Clase> clasesAsistidas;
    private int asistencias;

    public Inscripcion(Asignatura asignatura, Alumno alumno){
        clasesAsistidas = new LinkedList<>();
        this.asignatura = asignatura;
        this.alumno = alumno;
        asistencias = 0;
    }
    public String Condicion() {
        if(Promociona()){
            return "En Condiciones De Promocionar";
        }
        else if(Habilita()){
            return "En Condiciones De Habilitar";
        }
        else return "Libre";
    }


    public abstract boolean Promociona();
    public  abstract boolean Habilita();

    public Asignatura getAsignatura(){
        return asignatura;
    }
    public int getAsistencias(){
        return asistencias;
    }

    public Alumno getAlumno(){
        return  alumno;
    }

    public void marcaAsistencia(Clase clase){
        asistencias++;
        clasesAsistidas.add(clase);
    }
    //agregar metodos

}

