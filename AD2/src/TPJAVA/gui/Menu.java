package TPJAVA.gui;

import TPJAVA.domain.alumnos.Alumno;
import TPJAVA.domain.asignaturas.Asignatura;
import TPJAVA.domain.clase.Clase;
import TPJAVA.domain.persistencia.CargaDatos;
import TPJAVA.domain.persistencia.Persistencia;
import TPJAVA.domain.reportes.Reportes;
import TPJAVA.domain.asignaturas.exceptions.NoEncuentraAsignaturaException;
import TPJAVA.domain.universidad.Universidad;

import javax.swing.*;
import java.awt.*;
import java.io.*;
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
        JButton btnReset = new JButton("Resetear Datos");

        panelAcciones.add(btnAsistencia);
        panelAcciones.add(btnRanking);
        panelAcciones.add(btnDetalle);
        panelAcciones.add(btnLibres);
        panelAcciones.add(btnReset);

        gbc.gridy = 1;
        ventana.add(panelAcciones, gbc);

        // Lógica de botones
        btnRanking.addActionListener(e -> mostrarRanking(ventana));
        btnDetalle.addActionListener(e -> mostrarDetalleCatedra(ventana));
        btnLibres.addActionListener(e -> mostrarLibres(ventana));
        btnAsistencia.addActionListener(e -> registrarAsistencia(ventana));
        btnReset.addActionListener(e -> BotonReset(ventana));

        ventana.pack();
        ventana.setSize(500, 500);
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }

    private static void mostrarRanking(JFrame parent) {
        StringBuilder sb = new StringBuilder("Top 3 Asignaturas:\n");
        sb.append("\n");
        try(PrintWriter write = new PrintWriter(new FileWriter("ReporteRankingPresentismo.txt"))){
            List ranking = Reportes.rankingPresentismo();
            for (Object o : ranking) {
                sb.append(o.toString()).append("\n") ;
            }
            write.println(sb);

        } catch (Exception e) {
            e.printStackTrace();
        }
        JOptionPane.showMessageDialog(parent, new JScrollPane(new JTextArea(sb.toString(), 10, 30)));
    }

    private static void mostrarDetalleCatedra(JFrame parent) {
        String cod = JOptionPane.showInputDialog(parent, "Ingrese código de asignatura:").toUpperCase();
        if (cod != null) {
            try(PrintWriter write = new PrintWriter(new FileWriter("ReporteDetalleCatedra.txt"))){
                write.println(cod);
                String detalle = Reportes.detalleCatedra(cod);
                write.println(detalle);
                JTextArea area = new JTextArea(detalle, 15, 40);
                JOptionPane.showMessageDialog(parent, new JScrollPane(area));
            } catch (NoEncuentraAsignaturaException e) {
                JOptionPane.showMessageDialog(parent, "Asignatura no encontrada.");
            } catch (IOException e) {
                e.printStackTrace();
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
            if (seleccion >= 0 && seleccion <= 5) {
                anio = seleccion + 1;
            }
            StringBuilder sb = new StringBuilder();
            if (anio != 6) {
                sb.append("Alumnos LIBRES para el Año: ").append(anio).append("\n");
            } else {
                sb.append("Alumnos LIBRES en TODAS las asignaturas:\n");
            }

            String informe = Reportes.listarAlumnosLibres(anio);

            if (informe == null || informe.trim().isEmpty()) {

                String mensajeVacio;
                if (anio != 6) {
                    mensajeVacio = "No hay alumnos libres en el Año " + anio;
                } else {
                    mensajeVacio = "No hay alumnos libres en ningún año.";
                }
                JOptionPane.showMessageDialog(parent, mensajeVacio, "Aviso", JOptionPane.INFORMATION_MESSAGE);
            }else{

                try(PrintWriter write = new PrintWriter(new FileWriter("ReporteAlumnosLibres.txt"))){
                    write.println(sb);
                    write.println(informe);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                JTextArea area = new JTextArea(informe, 15, 40);
                area.setEditable(false);
                area.setCaretPosition(0);
                JOptionPane.showMessageDialog(parent, new JScrollPane(area), "Listado de Alumnos", JOptionPane.INFORMATION_MESSAGE);
            }

    }

    private static void registrarAsistencia(JFrame parent) {
        String codAsig = JOptionPane.showInputDialog(parent, "Código de Asignatura:").toUpperCase();
        if (codAsig == null) return;

        String idClase = JOptionPane.showInputDialog(parent, "ID de la Clase:").toUpperCase();
        if (idClase == null) return;

        String matAlumno = JOptionPane.showInputDialog(parent, "Matrícula del Alumno:").toUpperCase();
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
            Persistencia.guardarUniversidad();
            JOptionPane.showMessageDialog(parent, "Asistencia registrada correctamente para " + alu.getNombreYApellido());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(parent, "Error: " + e.getMessage());
        }
    }
    private static void BotonReset(JFrame parent) {

            int confirm = JOptionPane.showConfirmDialog(
                    parent,
                    "¿Está seguro de que desea borrar todos los datos y restaurar la carga inicial?",
                    "Confirmar Reset",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {
                Persistencia.borrarDatos();
                Universidad.setInstancia(null);
                JOptionPane.showMessageDialog(parent, "Los datos se reiniciaron por defecto");
                try{
                    CargaDatos.cargarDesdeXML();
                }catch ( java.lang.Exception er){

                }
            }

    }
}