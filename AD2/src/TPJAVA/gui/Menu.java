package TPJAVA.gui;

import javax.swing.*;

public class Menu {

    public static void abreMenu(){
        JFrame ventana = new JFrame("Menu");
        ventana.setSize(500,500);

        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }
}
