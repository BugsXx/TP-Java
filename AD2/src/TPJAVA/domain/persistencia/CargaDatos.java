package TPJAVA.domain.persistencia;

import TPJAVA.domain.alumnos.Alumno;
import TPJAVA.domain.asignaturas.*;
import TPJAVA.domain.clase.Clase;
import TPJAVA.domain.persistencia.exceptions.NoExisteElArchivoException;
import TPJAVA.domain.universidad.Universidad;
import TPJAVA.domain.universidad.exceptions.AsignaturaExistenteException;
import TPJAVA.domain.universidad.exceptions.YaEstaInscriptoElAlumnoALaUniversidadException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.HashMap;
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
        File archivo = new File("AD2/cargaInicial.xml");
        if (!archivo.exists()) {
            throw new NoExisteElArchivoException("El archivo " + archivo.getAbsolutePath() + " no existe");
        }

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
            String tipo = el.getAttribute("tipo").toLowerCase().trim();
            String cod = el.getAttribute("cod").trim();
            String nombre = el.getAttribute("nombre").trim();
            boolean promocionable = Boolean.parseBoolean(el.getAttribute("promocionable"));
            int cuatrimestre = Integer.parseInt(el.getAttribute("cuatrimestre"));
            int clasesTotales = Integer.parseInt(el.getAttribute("clasesTotales"));

            Asignatura asig = null;
            switch (tipo) {
                case "obligatoria": asig = new AsignaturaObligatoria(cod, nombre, promocionable, cuatrimestre, clasesTotales); break;
                case "optativa": asig = new AsignaturaOptativa(cod, nombre, promocionable, cuatrimestre, clasesTotales); break;
                case "pasantia": case "pasantiaytesis": asig = new PasantiaYTesis(cod, nombre, promocionable, cuatrimestre, clasesTotales); break;
            }

            if (asig != null) {
                try {
                    uni.agregarAsignatura(asig);

                    // Cargar clases de la asignatura
                    NodeList listaClases = el.getElementsByTagName("clase");
                    for (int k = 0; k < listaClases.getLength(); k++) {
                        Element elClase = (Element) listaClases.item(k);
                        Clase nuevaClase = new Clase(elClase.getAttribute("id"), elClase.getAttribute("fecha"), asig);
                        asig.creaClase(nuevaClase);
                    }
                } catch (Exception e) {
                    System.out.println("Nota: Error cargando asignatura o clase " + cod + ": " + e.getMessage());
                }
            }
        }

        // Cargar Alumnos
        NodeList listaAlumnos = doc.getElementsByTagName("alumno");
        for (int i = 0; i < listaAlumnos.getLength(); i++) {
            Element elAlumno = (Element) listaAlumnos.item(i);
            String mat = elAlumno.getAttribute("matricula").trim();
            String nom = elAlumno.getAttribute("nombreYApellido").trim();
            String fec = elAlumno.getAttribute("fechaNacimiento").trim();

            Alumno alu = new Alumno(mat, nom, fec);
            try {
                uni.agregaAlumno(alu);
            } catch (YaEstaInscriptoElAlumnoALaUniversidadException e) {
                System.out.println("Nota: Alumno " + mat + " ya existe.");
            }
            mapaAlumnos.put(mat, alu);
        }

        // Procesar Inscripciones
        for (int i = 0; i < listaAlumnos.getLength(); i++) {
            Element elAlumno = (Element) listaAlumnos.item(i);
            String mat = elAlumno.getAttribute("matricula").trim();
            Alumno alu = mapaAlumnos.get(mat);

            NodeList listaInscripciones = elAlumno.getElementsByTagName("inscripcion");
            for (int j = 0; j < listaInscripciones.getLength(); j++) {
                Element elInsc = (Element) listaInscripciones.item(j);
                String codAsig = elInsc.getAttribute("asignaturaCod").trim();
                char cond = elInsc.getAttribute("condicion").trim().charAt(0);
                try {
                    Asignatura asig = uni.encuentraAsignatura(codAsig);
                    asig.inscribirse(alu, cond);
                } catch (Exception e) {
                    System.err.println("ERROR inscribiendo a " + mat + " en " + codAsig + ": " + e.getMessage());
                }
            }
        }
    }
}