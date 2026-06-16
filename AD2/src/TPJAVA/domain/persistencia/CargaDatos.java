package TPJAVA.domain.persistencia;

import TPJAVA.domain.alumnos.Alumno;
import TPJAVA.domain.asignaturas.Asignatura;
import TPJAVA.domain.asignaturas.AsignaturaObligatoria;
import TPJAVA.domain.asignaturas.AsignaturaOptativa;
import TPJAVA.domain.asignaturas.PasantiaYTesis;
import TPJAVA.domain.universidad.Universidad;
import TPJAVA.domain.universidad.exceptions.AsignaturaExistenteException;
import TPJAVA.domain.universidad.exceptions.YaEstaInscriptoElAlumnoALaUniversidadException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

// Importá tus modelos correspondientes
// import TPJAVA.modelo.*;

public class CargaDatos {

    public static boolean cargarDesdeXML(File archivo) throws AsignaturaExistenteException, YaEstaInscriptoElAlumnoALaUniversidadException {
        if (archivo == null || !archivo.exists()) {
            System.err.println("Error: El archivo no existe o es nulo.");
            return false;
        }

        try {
            // 1. Configurar y parsear el documento XML
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(archivo);
            doc.getDocumentElement().normalize();

            // 2. Obtener la instancia de la Universidad (Singleton)
            Universidad uni = Universidad.getUniversidad();

            // 3. Procesar e instanciar ASIGNATURAS (Polimorfismo)
            NodeList listaAsignaturas = doc.getElementsByTagName("asignatura");
            for (int i = 0; i < listaAsignaturas.getLength(); i++) {
                Node nodo = listaAsignaturas.item(i);

                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element elemento = (Element) nodo;

                    // Detectar el tipo de asignatura (obligatoria, optativa, pasantia)
                    String tipo = elemento.getAttribute("tipo").toLowerCase().trim();

                    // Extraer los datos comunes de la herencia
                    String cod = elemento.getAttribute("cod");
                    String nombre = elemento.getAttribute("nombre");
                    boolean promocionable = Boolean.parseBoolean(elemento.getAttribute("promocionable"));
                    int cuatrimestre = Integer.parseInt(elemento.getAttribute("cuatrimestre"));
                    int clasesTotales = Integer.parseInt(elemento.getAttribute("clasesTotales"));

                    // Declaramos la variable abstracta padre
                    Asignatura asig = null;

                    // Decidir qué subclase instanciar según el tipo
                    switch (tipo) {
                        case "obligatoria":
                            asig = new AsignaturaObligatoria(cod, nombre, promocionable, cuatrimestre, clasesTotales);
                            break;
                        case "optativa":
                            asig = new AsignaturaOptativa(cod, nombre, promocionable, cuatrimestre, clasesTotales);
                            break;
                        case "pasantia":
                        case "pasantiaytesis":
                            asig = new PasantiaYTesis(cod, nombre, promocionable, cuatrimestre, clasesTotales);
                            break;
                        default:
                            System.out.println("Tipo de asignatura desconocido: " + tipo);
                            continue; // Salta esta iteración si el tipo no es válido
                    }

                    // Guardar la subclase en la lista genérica de la Universidad
                    if (asig != null) {
                        uni.agregarAsignatura(asig);
                    }
                }
            }

            // 4. Procesar e instanciar ALUMNOS
            NodeList listaAlumnos = doc.getElementsByTagName("alumno");
            for (int i = 0; i < listaAlumnos.getLength(); i++) {
                Node nodo = listaAlumnos.item(i);

                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element elemento = (Element) nodo;

                    String matricula = elemento.getAttribute("matricula");
                    String nombreYApellido = elemento.getAttribute("nombreYApellido");
                    String fechaNacimiento = elemento.getAttribute("fechaNacimiento");

                    Alumno alu = new Alumno(matricula, nombreYApellido, fechaNacimiento);
                    uni.agregaAlumno(alu);
                }
            }

            return true;

        } catch (Exception e) {
            System.err.println("Error al procesar el archivo XML: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}