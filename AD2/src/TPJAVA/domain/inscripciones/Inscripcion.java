package TPJAVA.domain.inscripciones;

import TPJAVA.domain.alumnos.Alumno;
import TPJAVA.domain.asignaturas.Asignatura;
import TPJAVA.domain.clase.Clase;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public abstract class Inscripcion implements Serializable {

    private Asignatura asignatura;
    private Alumno alumno;
    private List<Clase> clasesAsistidas;
    private int asistencias;

    public Inscripcion(Asignatura asignatura, Alumno alumno){
        clasesAsistidas = new LinkedList<>();
        this.asignatura = asignatura;
        this.alumno = alumno;
        asistencias = 0;
    }

    public String condicion() {
        if(promociona()){
            return "En Condiciones De Promocionar";
        }
        else if(habilita()){
            return "En Condiciones De Habilitar";
        }
        else return "Libre";
    }

    public abstract boolean promociona();
    public  abstract boolean habilita();
    public abstract String modalidad();

    public Asignatura getAsignatura(){
        return asignatura;
    }
    public int getAsistencias(){
        return asistencias;
    }
    public Alumno getAlumno(){
        return  alumno;
    }
    public List<Clase> getClasesAsistidas(){
        return clasesAsistidas;
    }

    public void marcaAsistencia(Clase clase){
        asistencias++;
        clasesAsistidas.add(clase);
    }


    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        Iterator<Clase> it = clasesAsistidas.iterator();
        sb.append("Lista de clases:\n");

        while (it.hasNext()) {
            Clase claseActual = it.next();

            sb.append("ID de la clase: ").append(claseActual.getId()).append("\n");
            sb.append("Fecha y hora de dictado de la clase: ").append(claseActual.getFechaYHoraDictado()).append("\n");
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Inscripcion)
            return ((Inscripcion) obj).asignatura.equals(this.asignatura) && ((Inscripcion) obj).alumno.equals(this.alumno);
        else  return false;
    }
}

