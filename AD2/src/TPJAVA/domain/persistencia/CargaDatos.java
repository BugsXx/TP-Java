package TPJAVA.domain.persistencia;

import TPJAVA.domain.alumnos.Alumno;
import TPJAVA.domain.asignaturas.*;
import TPJAVA.domain.clase.Clase;
import TPJAVA.domain.persistencia.exceptions.NoExisteElArchivoException;
import TPJAVA.domain.universidad.Universidad;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CargaDatos {

    public static void cargarDatos() throws Exception {
        try {
            Persistencia.cargarUniversidad();
        } catch (Exception e) {
            cargarDesdeXML();
        }
    }

    public static void cargarDesdeXML() throws Exception {
        List<String> errores = new ArrayList<>();
        File archivo = new File("AD2/cargaInicial.xml");
        if (!archivo.exists()) throw new NoExisteElArchivoException("No existe: " + archivo.getAbsolutePath());

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(archivo);
        doc.getDocumentElement().normalize();

        Universidad uni = Universidad.getUniversidad();
        Map<String, Alumno> mapaAlumnos = new HashMap<>();

        // Cargar Asignaturas y sus Clases
        NodeList listaAsignaturas = doc.getElementsByTagName("asignatura");
        for (int i = 0; i < listaAsignaturas.getLength(); i++) {
            Element el = (Element) listaAsignaturas.item(i);
            String cod = el.getAttribute("cod");
            try {
                String tipo = el.getAttribute("tipo").toLowerCase().trim();
                Asignatura asig = null;
                switch (tipo) {
                    case "obligatoria": asig = new AsignaturaObligatoria(cod, el.getAttribute("nombre"), Boolean.parseBoolean(el.getAttribute("promocionable")), Integer.parseInt(el.getAttribute("cuatrimestre")), Integer.parseInt(el.getAttribute("clasesTotales"))); break;
                    case "optativa": asig = new AsignaturaOptativa(cod, el.getAttribute("nombre"), Boolean.parseBoolean(el.getAttribute("promocionable")), Integer.parseInt(el.getAttribute("cuatrimestre")), Integer.parseInt(el.getAttribute("clasesTotales"))); break;
                    case "pasantia": asig = new PasantiaYTesis(cod, el.getAttribute("nombre"), Boolean.parseBoolean(el.getAttribute("promocionable")), Integer.parseInt(el.getAttribute("cuatrimestre")), Integer.parseInt(el.getAttribute("clasesTotales"))); break;
                    default: errores.add("Asignatura " + cod + ": Tipo desconocido.");
                }

                if (asig != null) {
                    uni.agregarAsignatura(asig);
                    NodeList listaClases = el.getElementsByTagName("clase");
                    for (int k = 0; k < listaClases.getLength(); k++) {
                        Element elCl = (Element) listaClases.item(k);
                        asig.creaClase(new Clase(elCl.getAttribute("id"), elCl.getAttribute("fecha"), asig));
                    }
                }
            } catch (Exception e) {
                errores.add("Error procesando asignatura " + cod + ": " + e.getMessage());
            }
        }

        // Cargar Alumnos
        NodeList listaAlumnos = doc.getElementsByTagName("alumno");
        for (int i = 0; i < listaAlumnos.getLength(); i++) {
            Element elA = (Element) listaAlumnos.item(i);
            String mat = elA.getAttribute("matricula");
            Alumno alu = new Alumno(mat, elA.getAttribute("nombreYApellido"), elA.getAttribute("fechaNacimiento"));
            try { uni.agregaAlumno(alu); } catch (Exception e) { errores.add("Alumno " + mat + ": " + e.getMessage()); }
            mapaAlumnos.put(mat, alu);
        }

        // Procesar Inscripciones y Asistencias
        for (int i = 0; i < listaAlumnos.getLength(); i++) {
            Element elA = (Element) listaAlumnos.item(i);
            Alumno alu = mapaAlumnos.get(elA.getAttribute("matricula"));
            NodeList listaInsc = elA.getElementsByTagName("inscripcion");
            for (int j = 0; j < listaInsc.getLength(); j++) {
                Element elInsc = (Element) listaInsc.item(j);
                String codAsig = elInsc.getAttribute("asignaturaCod");
                try {
                    Asignatura asig = uni.encuentraAsignatura(codAsig);
                    if (asig == null) throw new Exception("Asignatura no encontrada");
                    asig.inscribirse(alu, elInsc.getAttribute("condicion").charAt(0));

                    NodeList listaAsist = elInsc.getElementsByTagName("asistencia");
                    for (int k = 0; k < listaAsist.getLength(); k++) {
                        Clase clase = asig.getClase(((Element) listaAsist.item(k)).getAttribute("claseId"));
                        if (clase != null) clase.tomaAsistencia(alu);
                        else errores.add("Alumno " + alu.getMatricula() + ": Clase no encontrada en " + codAsig);
                    }
                } catch (Exception e) { errores.add("Inscripción error (Mat: " + alu.getMatricula() + ", Asig: " + codAsig + "): " + e.getMessage()); }
            }
        }

        if (!errores.isEmpty()) {
            StringBuilder sb = new StringBuilder("Se detectaron errores durante la carga inicial:\n\n");
            for (String err : errores) sb.append("- ").append(err).append("\n");
            JOptionPane.showMessageDialog(null, new JScrollPane(new JTextArea(sb.toString(), 15, 50)), "Errores de Consistencia", JOptionPane.WARNING_MESSAGE);
        }

        Persistencia.guardarUniversidad();
    }
}