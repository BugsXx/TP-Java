package TPJAVA.domain.persistencia;


import java.io.*;
import java.util.List;

public class Persistencia {
    private static final String UNIVERSIDAD_FILE="Universidad.dat";

    private static <T> void guardarlista(List <T> lista, String archivo){
        try {
            ObjectOutputStream OOS = new ObjectOutputStream(new FileOutputStream(archivo));
            OOS.writeObject(lista);
        } catch (IOException e) {
            throw new RuntimeException("Error al guardar" + archivo,e);
        }
    }

    private static <T> List <T> cargarlista(String archivo){
        try {
            ObjectInputStream OIS = new ObjectInputStream(new FileInputStream(archivo));
            return (List<T>)OIS.readObject();
        } catch (IOException e) {
            throw new RuntimeException("Error al leer" + archivo,e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("No se encontro el objeto");
        }
    }
    
}
