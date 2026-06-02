package TPJAVA.domain;

import TPJAVA.domain.asignaturas.Asignatura;
import TPJAVA.domain.asignaturas.exceptions.NoEncuentraAsignaturaException;
import TPJAVA.domain.inscripciones.Inscripcion;

import java.util.Iterator;

public class Reportes {


    public void detalleAsignatura(String cod, Universidad universidad){ // luego con swing ver como mostrarlo
        StringBuilder sb = new StringBuilder();
        Asignatura asignaturaActual = null;
        try{
            asignaturaActual = universidad.encuentraAsignatura(cod);
            Iterator<Inscripcion> itInsc= asignaturaActual.getInscripciones().iterator();
            sb.append("Nombre de la asignatura: ").append(asignaturaActual.getNombre()).append("\n");
            while(itInsc.hasNext()){
                Inscripcion inscripcionActual = itInsc.next();

                sb.append("Datos del alumno:\n\t").append(inscripcionActual.getAlumno().muestra());
                sb.append("\n");

                sb.append("\t");
                sb.append(inscripcionActual.muestraClases());

                sb.append("\tPorcentaje de asistencia: ").append(inscripcionActual.getAsistencias()/asignaturaActual.getClasesTotales() * 100);
                sb.append("\tModalidad: ").append(inscripcionActual.Modalidad());
                sb.append("\tCondicion Academica: ").append(inscripcionActual.Condicion());
            }
        }
        catch(NoEncuentraAsignaturaException e){
            sb.append("");
        }
    }

    public void detalleCatedraLibres(Universidad universidad, String cod, int C1, int C2){
        StringBuilder sb = new StringBuilder();
        sb.append("Detalle de los alumnos libres\n");
        try{
            Asignatura asignaturaActual = universidad.encuentraAsignatura(cod);
            int cuatrimestre = asignaturaActual.getCuatrimestre();
            if(cuatrimestre >= C1 && cuatrimestre <= C2){
                Iterator<Inscripcion> itAsig = asignaturaActual.getInscripciones().iterator();
                while (itAsig.hasNext()) {
                    Inscripcion inscripcionActual = itAsig.next();
                    if(inscripcionActual.Condicion().equals("Libre"))
                        sb.append(inscripcionActual.getAlumno().muestra()).append("\n");
                }
            }
        }catch(NoEncuentraAsignaturaException e){
            sb.append("");
        }

    }

    public void rankingPresentismo(Universidad universidad){
        // desarrollar
    }
}
