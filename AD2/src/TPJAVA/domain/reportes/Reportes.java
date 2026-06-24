package TPJAVA.domain.reportes;

import TPJAVA.domain.alumnos.Alumno;
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

        Iterator<Inscripcion> it = asignaturaActual.getInscripciones().iterator();

        while (it.hasNext()) {
            Inscripcion insc = it.next();
            Alumno alumnoActual = insc.getAlumno();
            float total = asignaturaActual.getClasesTotales();
            float asistencia = (total > 0) ? (insc.getAsistencias() / total) * 100 : 0.0f;

            sb.append("\n--- Alumno ---\n");
            sb.append("Nombre: ").append(alumnoActual.getNombreYApellido()).append("\n");
            sb.append("Matrícula: ").append(alumnoActual.getMatricula()).append("\n");
            sb.append("Fecha Nacimiento: ").append(alumnoActual.getFechaNacimiento()).append("\n");
            sb.append("Asistencia: ").append(String.format("%.1f", asistencia)).append("%\n");
            sb.append("Modalidad: ").append(insc.modalidad()).append("\n");
            sb.append("Condición: ").append(insc.condicion()).append("\n");
        }
        return sb.toString();
    }

    public static String listarAlumnosLibres(Integer anio) {
        Universidad uni = Universidad.getUniversidad();
        StringBuilder sb = new StringBuilder();

        for (Asignatura asig : uni.getAsignaturas()) {

            boolean filtrar = anio.equals(6) ||
                    (asig.getCuatrimestre() > (anio - 1) * 2 && asig.getCuatrimestre() <= anio * 2);

            if (filtrar) {
                StringBuilder alumnosAsigSB = new StringBuilder();
                boolean tieneLibres = false;

                for (Inscripcion insc : asig.getInscripciones()) {
                    if (insc.condicion().equals("Libre")) {
                        alumnosAsigSB.append(" - ")
                                .append(insc.getAlumno().getNombreYApellido())
                                .append(" (").append(insc.getAlumno().getMatricula()).append(")\n");
                        tieneLibres = true;
                    }
                }

                if (tieneLibres) {
                    sb.append("\nAsignatura: ").append(asig.getNombre()).append("\n");
                    sb.append(alumnosAsigSB);
                }
            }
        }
        return sb.toString();
    }

    public static List<Asignatura> rankingPresentismo() {
        List<Asignatura> lista = new ArrayList<>(Universidad.getUniversidad().getAsignaturas());

        lista.sort((a1, a2) -> Float.compare(a2.calculaPresentismo(), a1.calculaPresentismo()));

        return lista.subList(0, Math.min(3, lista.size()));
    }
}