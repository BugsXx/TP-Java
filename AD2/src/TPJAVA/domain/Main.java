package TPJAVA.domain;

import TPJAVA.domain.universidad.Universidad;
import TPJAVA.gui.Menu;

public class Main {
    public static void main(String[] args) {
        Menu.abreMenu(); //inicio del flow de la app
        Universidad universidad = Universidad.getUniversidad();

    }


}