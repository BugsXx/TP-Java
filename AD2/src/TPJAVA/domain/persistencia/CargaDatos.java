package TPJAVA.domain.persistencia;

import TPJAVA.domain.alumnos.Alumno;
import TPJAVA.domain.asignaturas.Asignatura;
import TPJAVA.domain.asignaturas.AsignaturaObligatoria;
import TPJAVA.domain.asignaturas.AsignaturaOptativa;
import TPJAVA.domain.asignaturas.PasantiaYTesis;
import TPJAVA.domain.persistencia.exceptions.NoExisteElArchivoException;
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

    public static void cargarDatos()throws  java.lang.ClassNotFoundException, NoExisteElArchivoException, AsignaturaExistenteException, org.xml.sax.SAXException, java.io.IOException, YaEstaInscriptoElAlumnoALaUniversidadException,javax.xml.parsers.ParserConfigurationException{
        try{
            Persistencia.cargarUniversidad();

        }catch(NoExisteElArchivoException e){
            CargaDatos.cargarDesdeXML();
        }



    }
    public static void cargarDesdeXML() throws NoExisteElArchivoException, AsignaturaExistenteException, org.xml.sax.SAXException, java.io.IOException, YaEstaInscriptoElAlumnoALaUniversidadException,javax.xml.parsers.ParserConfigurationException {
        File archivo = new File("cargaInicial.xml");;
        if (archivo == null || !archivo.exists()) {
            throw new NoExisteElArchivoException("El archivo" + archivo.toString() + "no existe");
        }


        // Configurar y parsear el documento XML
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(archivo);
        doc.getDocumentElement().normalize();

        // Obtener la instancia de la Universidad (Singleton)
        Universidad uni = Universidad.getUniversidad();

        // Procesar e instanciar ASIGNATURAS (Polimorfismo)
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

        // Procesar e instanciar ALUMNOS
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

    }
}