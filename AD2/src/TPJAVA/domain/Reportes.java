package TPJAVA.domain;

import TPJAVA.domain.asignaturas.Asignatura;
import TPJAVA.domain.asignaturas.exceptions.NoEncuentraAsignaturaException;
import TPJAVA.domain.inscripciones.Inscripcion;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

public class Reportes {

    private Reportes(){
    }

    public static void detalleCatedra(String cod) throws NoEncuentraAsignaturaException{ // luego con swing ver como mostrarlo
        Universidad universidad = Universidad.getUniversidad();
        StringBuilder sb = new StringBuilder();
        Asignatura asignaturaActual = null;
        asignaturaActual = universidad.encuentraAsignatura(cod);
        Iterator<Inscripcion> itInsc= asignaturaActual.getInscripciones().iterator();
        sb.append("Nombre de la asignatura: ").append(asignaturaActual.getNombre()).append("\n");
        while(itInsc.hasNext()){
            Inscripcion inscripcionActual = itInsc.next();

            sb.append("Datos del alumno:\n\t").append(inscripcionActual.getAlumno());
            sb.append("\n");

            sb.append("\t");
            sb.append(inscripcionActual);

            sb.append("\tPorcentaje de asistencia: ").append(inscripcionActual.getAsistencias()/asignaturaActual.getClasesTotales() * 100);
            sb.append("\tModalidad: ").append(inscripcionActual.Modalidad());
            sb.append("\tCondicion Academica: ").append(inscripcionActual.Condicion());
        }

    }


    public static void detalleCatedraLibres(String cod, int C1, int C2)throws NoEncuentraAsignaturaException{
        Universidad universidad = Universidad.getUniversidad();
        StringBuilder sb = new StringBuilder();
        sb.append("Detalle de los alumnos libres\n");

            Asignatura asignaturaActual = universidad.encuentraAsignatura(cod);
            int cuatrimestre = asignaturaActual.getCuatrimestre();
            if(cuatrimestre >= C1 && cuatrimestre <= C2){
                Iterator<Inscripcion> itAsig = asignaturaActual.getInscripciones().iterator();
                while (itAsig.hasNext()) {
                    Inscripcion inscripcionActual = itAsig.next();
                    if(inscripcionActual.Condicion().equals("Libre"))
                        sb.append(inscripcionActual.getAlumno()).append("\n");
                }
            }
        }



    public static List rankingPresentismo(){
        Universidad universidad = Universidad.getUniversidad();

        TreeSet asignaturas = universidad.getAsignaturas();
        List ranking = new ArrayList<>();
        Iterator <TreeSet> it = asignaturas.iterator();
        int i = 0;
        while(it.hasNext() && i < 3){
            ranking.add(it.next());
            i++;
        }
        return ranking;
    }

}
