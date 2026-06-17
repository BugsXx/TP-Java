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

        // Titulo
        JLabel lblTitulo = new JLabel("SISTEMA ACADÉMICO", JLabel.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
        gbc.gridy = 0;
        ventana.add(lblTitulo, gbc);

        // Panel de Acciones (Visible desde el inicio)
        JPanel panelAcciones = new JPanel(new GridLayout(4, 1, 10, 10));
        JButton btnAsistencia = new JButton("Registrar Asistencia");
        JButton btnRanking = new JButton("Ver Ranking de Asignaturas");
        JButton btnDetalle = new JButton("Detalle de Alumnos");
        JButton btnLibres = new JButton("Alumnos Libres / Errores");

        panelAcciones.add(btnAsistencia);
        panelAcciones.add(btnRanking);
        panelAcciones.add(btnDetalle);
        panelAcciones.add(btnLibres);
        panelAcciones.setVisible(true);
        gbc.gridy = 1;
        ventana.add(panelAcciones, gbc);

        // Lógica de los botones
        btnAsistencia.addActionListener(ev -> JOptionPane.showMessageDialog(ventana, "Abriendo formulario de asistencia..."));
        btnRanking.addActionListener(ev -> JOptionPane.showMessageDialog(ventana, "Generando ranking de presentismo..."));
        btnDetalle.addActionListener(ev -> JOptionPane.showMessageDialog(ventana, "Mostrando detalle de alumnos..."));
        btnLibres.addActionListener(ev -> JOptionPane.showMessageDialog(ventana, "Abriendo panel de alumnos libres..."));

        // Pack inicial y visibilidad
        ventana.pack();
        ventana.setSize(400, 350); // Ajustado a un tamaño más compacto al quitar el botón
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }
}
