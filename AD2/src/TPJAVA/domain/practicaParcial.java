package TPJAVA.domain;

import TPJAVA.domain.alumnos.Alumno;
import TPJAVA.domain.asignaturas.Asignatura;
import TPJAVA.domain.universidad.Universidad;
import java.util.*;

public class practicaParcial {



    public void metodo1() {
        Iterator itAsig = Universidad.getUniversidad().getAsignaturas().iterator();
        Iterator itAlum = Universidad.getUniversidad().getAlumnos().iterator();


        while(itAsig.hasNext() && itAlum.hasNext()) {
            Asignatura asig = (Asignatura) itAsig.next();

            Alumno alum = (Alumno) itAlum.next();

            System.out.println(asig.toString() + " - " + alum.toString());
        }

    }


}
