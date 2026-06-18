package TPJAVA.domain.persistencia;

import TPJAVA.domain.persistencia.exceptions.NoExisteElArchivoException;
import TPJAVA.domain.universidad.Universidad;
import java.io.*;

public class Persistencia {
    private static final String UNIVERSIDAD_FILE = "Universidad.dat";

    // Guardar toda la instancia del Singleton
    public static void guardarUniversidad() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(UNIVERSIDAD_FILE))) {
            oos.writeObject(Universidad.getUniversidad());
        } catch (IOException e) {
            throw new RuntimeException("Error al guardar la universidad", e);
        }
    }

    // Cargar y restaurar el Singleton
    public static void cargarUniversidad() throws NoExisteElArchivoException,java.io.IOException, java.lang.ClassNotFoundException{
        File file = new File(UNIVERSIDAD_FILE);
        if (file == null || !file.exists()){
            throw new NoExisteElArchivoException("No existe el archivo Universidad.dat");
        }
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(UNIVERSIDAD_FILE));
        Universidad uniCargada = (Universidad) ois.readObject();
        Universidad.setInstancia(uniCargada);

    }

    public static void borrarDatos() {
        File file = new File("Universidad.dat");
        if (file.exists()) {
            file.delete();
        }
    }
}