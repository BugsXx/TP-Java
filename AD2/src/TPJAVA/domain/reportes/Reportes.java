package TPJAVA.domain.reportes;

import TPJAVA.domain.asignaturas.Asignatura;
import TPJAVA.domain.asignaturas.exceptions.NoEncuentraAsignaturaException;
import TPJAVA.domain.inscripciones.Inscripcion;
import TPJAVA.domain.universidad.Universidad;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Reportes {

    private Reportes() {}

    public static String detalleCatedra(String cod) throws NoEncuentraAsignaturaException {
        Universidad universidad = Universidad.getUniversidad();
        Asignatura asignaturaActual = universidad.encuentraAsignatura(cod);

        StringBuilder sb = new StringBuilder();
        sb.append("Nombre de la asignatura: ").append(asignaturaActual.getNombre()).append("\n");

        Iterator<Inscripcion> itInsc = asignaturaActual.getInscripciones().iterator();

        while (itInsc.hasNext()) {
            Inscripcion insc = itInsc.next();

            float total = asignaturaActual.getClasesTotales();
            float asistencia = (total > 0) ? (insc.getAsistencias() / total) * 100 : 0.0f;

            sb.append("\n--- Alumno ---\n");
            sb.append("Nombre: ").append(insc.getAlumno().getNombreYApellido()).append("\n");
            sb.append("Matrícula: ").append(insc.getAlumno().getMatricula()).append("\n");
            sb.append("Fecha Nacimiento: ").append(insc.getAlumno().getFechaNacimiento()).append("\n");
            sb.append("Asistencia: ").append(String.format("%.1f", asistencia)).append("%\n");
            sb.append("Modalidad: ").append(insc.Modalidad()).append("\n");
            sb.append("Condición: ").append(insc.Condicion()).append("\n");
        }
        return sb.toString();
    }

    public static String listarAlumnosLibres(Integer anio) {
        Universidad uni = Universidad.getUniversidad();
        StringBuilder sb = new StringBuilder();


        for (Asignatura asig : uni.getAsignaturas()) {
            boolean filtrar = (anio == 6) || (asig.getCuatrimestre() > (anio-1)*2 && asig.getCuatrimestre() <= anio*2);

            if (filtrar) {
                sb.append("\nAsignatura: ").append(asig.getNombre()).append("\n");
                boolean tieneLibres = false;
                for (Inscripcion insc : asig.getInscripciones()) {
                    if (insc.Condicion().equalsIgnoreCase("Libre")) {
                        sb.append(" - ").append(insc.getAlumno().getNombreYApellido()).append(" (").append(insc.getAlumno().getMatricula()).append(")\n");
                        tieneLibres = true;
                    }
                }
                if (!tieneLibres) sb.append(" (Sin alumnos libres)\n");
            }
        }
        return sb.toString();
    }

    public static List<Asignatura> rankingPresentismo() {
        List<Asignatura> lista = new ArrayList<>(Universidad.getUniversidad().getAsignaturas());

        lista.sort((a1, a2) -> {
            return Float.compare(a2.calculaPresentismo(), a1.calculaPresentismo());
        });

        return lista.subList(0, Math.min(3, lista.size()));
    }
}