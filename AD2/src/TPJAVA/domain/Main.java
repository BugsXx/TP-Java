package TPJAVA.domain;

import TPJAVA.domain.persistencia.CargaDatos;
import TPJAVA.domain.universidad.Universidad;
import TPJAVA.gui.Menu;

public class Main {
    public static void main(String[] args) {

        Menu.inicializaApp();
        Menu.abreMenu(); //inicio del flow de la app
        Universidad universidad = Universidad.getUniversidad();

    }


}