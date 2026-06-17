package TPJAVA.domain.persistencia;


import java.io.*;
import java.util.List;
import java.util.TreeSet;

public class Persistencia {
    private static final String UNIVERSIDAD_FILE="Universidad.dat";

    private static <T> void guardarArbol(TreeSet<T> arbol, String archivo){
        try {
            ObjectOutputStream OOS = new ObjectOutputStream(new FileOutputStream(archivo));
            OOS.writeObject(arbol);
        } catch (IOException e) {
            throw new RuntimeException("Error al guardar" + archivo,e);
        }
    }

    private static <T> TreeSet <T> cargarArbol(String archivo){
        try {
            ObjectInputStream OIS = new ObjectInputStream(new FileInputStream(archivo));
            return (TreeSet<T>)OIS.readObject();
        } catch (IOException e) {
            throw new RuntimeException("Error al leer" + archivo,e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("No se encontro el objeto");
        }
    }
    
}
