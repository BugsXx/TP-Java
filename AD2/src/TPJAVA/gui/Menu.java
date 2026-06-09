package TPJAVA.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Menu {

    public static void abreMenu(){
        JFrame ventana = new JFrame("Sistema Académico");
        ventana.setSize(500,500);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLocationRelativeTo(null);
        ventana.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15); // Márgenes entre elementos
        gbc.gridx = 0;

        // Título
        JLabel lblTitulo = new JLabel("SISTEMA ACADÉMICO", JLabel.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
        gbc.gridy = 0;
        ventana.add(lblTitulo, gbc);

        // Botón Cargar
        JButton btnCargar = new JButton("Cargar Archivo de Datos");
        btnCargar.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridy = 1;
        ventana.add(btnCargar, gbc);

        // Panel de Acciones (Oculto inicialmente)
        JPanel panelAcciones = new JPanel(new GridLayout(4, 1, 10, 10));
        JButton btnAsistencia = new JButton("Registrar Asistencia");
        JButton btnRanking = new JButton("Ver Ranking de Asignaturas");
        JButton btnDetalle = new JButton("Detalle de Alumnos");
        JButton btnLibres = new JButton("Alumnos Libres / Errores");

        panelAcciones.add(btnAsistencia);
        panelAcciones.add(btnRanking);
        panelAcciones.add(btnDetalle);
        panelAcciones.add(btnLibres);
        panelAcciones.setVisible(false);

        gbc.gridy = 2;
        ventana.add(panelAcciones, gbc);

        // LÓGICA CORREGIDA DEL BOTÓN DE CARGA
        btnCargar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser selector = new JFileChooser();
                int valor = selector.showOpenDialog(ventana);

                if (valor == JFileChooser.APPROVE_OPTION) {
                    File archivoseleccionado = selector.getSelectedFile();

                    if (archivoseleccionado != null) {
                        // Cambiamos el texto del botón de carga para mostrar el nombre del archivo
                        btnCargar.setText("Archivo: " + archivoseleccionado.getName());
                        btnCargar.setEnabled(false);

                        // Habilitamos las opciones para el usuario
                        panelAcciones.setVisible(true);
                        ventana.revalidate();
                        ventana.repaint();
                    } else {
                        JOptionPane.showMessageDialog(ventana, "Error: No se pudo obtener el archivo.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        // Lógica de los botones secundarios
        btnAsistencia.addActionListener(ev -> {
            JOptionPane.showMessageDialog(ventana, "Abriendo formulario de asistencia...");
        });

        btnRanking.addActionListener(ev -> {
            JOptionPane.showMessageDialog(ventana, "Generando ranking de presentismo...");
        });

        // Hacer visible la ventana al final
        ventana.setVisible(true);
    }
}

