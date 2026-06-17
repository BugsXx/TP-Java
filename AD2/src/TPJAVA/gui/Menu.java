package TPJAVA.gui;

import TPJAVA.domain.persistencia.CargaDatos;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;

public class Menu {

    public static void inicializaApp(){
        try {
            CargaDatos.cargarDatos();

        } catch (Exception e) {

        }
    }
    public static void abreMenu(){
        JFrame ventana = new JFrame("Sistema Académico");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
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

        // Lógica del botón de carga optimizada con Lambda
        btnCargar.addActionListener(e -> {
            JFileChooser selector = new JFileChooser();
            selector.setFileFilter(new FileNameExtensionFilter("Archivos de Datos (*.txt, *.csv)", "txt", "csv"));

            int valor = selector.showOpenDialog(ventana);

            if (valor == JFileChooser.APPROVE_OPTION) {
                File archivoseleccionado = selector.getSelectedFile();

                if (archivoseleccionado != null) {
                    btnCargar.setText("Archivo: " + archivoseleccionado.getName());
                    btnCargar.setEnabled(false);

                    panelAcciones.setVisible(true);
                    ventana.pack(); // Ajusta el tamaño dinámicamente
                    ventana.setLocationRelativeTo(null); // Mantiene centrado
                } else {
                    JOptionPane.showMessageDialog(ventana, "Error: No se pudo obtener el archivo.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Lógica de los botones secundarios
        btnAsistencia.addActionListener(ev -> JOptionPane.showMessageDialog(ventana, "Abriendo formulario de asistencia..."));
        btnRanking.addActionListener(ev -> JOptionPane.showMessageDialog(ventana, "Generando ranking de presentismo..."));
        btnDetalle.addActionListener(ev -> JOptionPane.showMessageDialog(ventana, "Mostrando detalle de alumnos..."));
        btnLibres.addActionListener(ev -> JOptionPane.showMessageDialog(ventana, "Abriendo panel de alumnos libres..."));

        // Pack inicial y visibilidad
        ventana.pack();
        ventana.setSize(500, Math.max(ventana.getHeight(), 400)); // Un tamaño inicial base elegante
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }
}
