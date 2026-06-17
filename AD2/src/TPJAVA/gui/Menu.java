package TPJAVA.gui;

import TPJAVA.domain.persistencia.CargaDatos;
import TPJAVA.domain.reportes.Reportes;
import TPJAVA.domain.asignaturas.exceptions.NoEncuentraAsignaturaException;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Menu {

    public static void inicializaApp(){
        try {
            CargaDatos.cargarDatos();
        } catch (Exception e) {
            System.err.println("Error al cargar los datos iniciales: " + e.getMessage());
        }
    }

    public static void abreMenu(){
        JFrame ventana = new JFrame("Sistema Académico");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;

        // Título
        JLabel lblTitulo = new JLabel("SISTEMA ACADÉMICO", JLabel.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
        gbc.gridy = 0;
        ventana.add(lblTitulo, gbc);

        // Panel de Acciones
        JPanel panelAcciones = new JPanel(new GridLayout(4, 1, 10, 10));
        JButton btnAsistencia = new JButton("Registrar Asistencia");
        JButton btnRanking = new JButton("Ver Ranking de Asignaturas");
        JButton btnDetalle = new JButton("Detalle de Alumnos");
        JButton btnLibres = new JButton("Alumnos Libres / Errores");

        panelAcciones.add(btnAsistencia);
        panelAcciones.add(btnRanking);
        panelAcciones.add(btnDetalle);
        panelAcciones.add(btnLibres);

        gbc.gridy = 1;
        ventana.add(panelAcciones, gbc);

        // Lógica de botones
        btnRanking.addActionListener(e -> mostrarRanking(ventana));
        btnDetalle.addActionListener(e -> mostrarDetalleCatedra(ventana));
        btnLibres.addActionListener(e -> mostrarLibres(ventana));
        btnAsistencia.addActionListener(e -> JOptionPane.showMessageDialog(ventana, "Funcionalidad en desarrollo"));

        ventana.pack();
        ventana.setSize(400, 400);
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }

    private static void mostrarRanking(JFrame parent) {
        List ranking = Reportes.rankingPresentismo();
        StringBuilder sb = new StringBuilder("Top 3 Asignaturas:\n\n");
        for (Object o : ranking) {
            sb.append(o.toString()).append("\n");
        }
        JOptionPane.showMessageDialog(parent, new JScrollPane(new JTextArea(sb.toString(), 10, 30)));
    }

    private static void mostrarDetalleCatedra(JFrame parent) {
        String cod = JOptionPane.showInputDialog(parent, "Ingrese código de asignatura:");
        if (cod != null) {
            try {
                String detalle = Reportes.detalleCatedra(cod);
                JTextArea area = new JTextArea(detalle, 15, 40);
                JOptionPane.showMessageDialog(parent, new JScrollPane(area));
            } catch (NoEncuentraAsignaturaException e) {
                JOptionPane.showMessageDialog(parent, "Asignatura no encontrada.");
            }
        }
    }

    private static void mostrarLibres(JFrame parent) {
        String cod = JOptionPane.showInputDialog(parent, "Ingrese código de asignatura:");
        if (cod != null) {
            try {
                // Asumiendo rango de cuatrimestres 1 a 2
                String informe = Reportes.detalleCatedraLibres(cod, 1, 2);
                JOptionPane.showMessageDialog(parent, new JScrollPane(new JTextArea(informe, 10, 30)));
            } catch (NoEncuentraAsignaturaException e) {
                JOptionPane.showMessageDialog(parent, "Asignatura no encontrada.");
            }
        }
    }
}