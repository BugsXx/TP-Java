package TPJAVA.gui;

import TPJAVA.domain.alumnos.Alumno;
import TPJAVA.domain.asignaturas.Asignatura;
import TPJAVA.domain.clase.Clase;
import TPJAVA.domain.persistencia.CargaDatos;
import TPJAVA.domain.reportes.Reportes;
import TPJAVA.domain.asignaturas.exceptions.NoEncuentraAsignaturaException;
import TPJAVA.domain.universidad.Universidad;

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
        btnAsistencia.addActionListener(e -> registrarAsistencia(ventana));

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
        Object[] opciones = {"Año 1", "Año 2", "Año 3", "Año 4", "Año 5", "Ver Todos"};

        int seleccion = JOptionPane.showOptionDialog(
                parent,
                "Seleccione el año a consultar:",
                "Listado de Alumnos Libres",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[5] // Opción por defecto
        );

        Integer anio = null;
        if (seleccion >= 0 && seleccion <= 4) { // Si eligió Año 1 a 5
            anio = seleccion + 1;
        }

        String informe = Reportes.listarAlumnosLibres(anio);

        JTextArea area = new JTextArea(informe, 15, 40);
        area.setEditable(false);
        JOptionPane.showMessageDialog(parent, new JScrollPane(area));
    }

    private static void registrarAsistencia(JFrame parent) {
        String codAsig = JOptionPane.showInputDialog(parent, "Código de Asignatura:");
        if (codAsig == null) return;

        String idClase = JOptionPane.showInputDialog(parent, "ID de la Clase:");
        if (idClase == null) return;

        String matAlumno = JOptionPane.showInputDialog(parent, "Matrícula del Alumno:");
        if (matAlumno == null) return;

        try {
            Universidad uni = Universidad.getUniversidad();

            // Buscamos la asignatura
            Asignatura asig = uni.encuentraAsignatura(codAsig);

            // Buscamos la clase en la lista de la asignatura
            Clase clase= asig.getClase(idClase);

            // Buscamos la referencia exacta del alumno en el TreeSet de la Universidad
            Alumno alu = null;
            for (Alumno a : uni.getAlumnos()) {
                if (a.getMatricula().equals(matAlumno)) {
                    alu = a;
                    break;
                }
            }

            if (clase == null || alu == null) {
                JOptionPane.showMessageDialog(parent, "Error: Clase o Alumno no encontrados.");
                return;
            }

            clase.tomaAsistencia(alu);

            JOptionPane.showMessageDialog(parent, "Asistencia registrada correctamente para " + alu.getNombreYApellido());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(parent, "Error: " + e.getMessage());
        }
    }}