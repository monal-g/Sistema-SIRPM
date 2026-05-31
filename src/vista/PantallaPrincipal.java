package vista;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import modelo.*;
import controlador.*;

public class PantallaPrincipal extends JFrame {


    private JLabel titulo;
    private JButton botonRegistrar;
    private JButton botonProcesar;
    private JButton botonLimpiar;
    private JButton botonCargar;
    private JButton botonResultados;
    private JButton botonSalir;
    private JPanel jpBotones;
    private List<OrdenEnvio> listaOrdenes;

    public PantallaPrincipal() {
        
        listaOrdenes = new ArrayList<>();
        //Configuracion de la ventana
        setTitle("SIRPM - Sistema de Procesamiento de Órdenes Marítimas");
        setSize(400, 350);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        //Componentes de la ventana + titulo
        titulo = new JLabel("SIRPM - Menú Principal", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 16));
        titulo.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));

        jpBotones = new JPanel(new GridLayout(6, 1, 10, 10));
        jpBotones.setBorder(BorderFactory.createEmptyBorder(20, 60, 20, 60));

        botonRegistrar  = new JButton("Registrar orden individual");
        botonProcesar   = new JButton("Procesar orden");
        botonLimpiar    = new JButton("Limpiar formulario");
        botonCargar     = new JButton("Cargar archivo masivo");
        botonResultados = new JButton("Ver resultados");
        botonSalir      = new JButton("Salir");


        agregarBotones();
        confiEventos();
    }


    private void agregarBotones() {

        add(titulo, BorderLayout.NORTH);

        jpBotones.add(botonRegistrar);
        jpBotones.add(botonProcesar);
        jpBotones.add(botonLimpiar);
        jpBotones.add(botonCargar);
        jpBotones.add(botonResultados);
        jpBotones.add(botonSalir);

        add(jpBotones, BorderLayout.CENTER);
    }

    private void confiEventos() {

        botonRegistrar.addActionListener(e -> new RegistroOrden().setVisible(true));
        botonProcesar.addActionListener(e -> procesarOrdenes());
        botonLimpiar.addActionListener(e -> {
            listaOrdenes.clear();
            JOptionPane.showMessageDialog(this, "Lista de órdenes limpia");
        });
        botonCargar.addActionListener(e -> cargarArchivo());
        botonResultados.addActionListener(e -> JOptionPane.showMessageDialog(this, "Órdenes cargadas: " + listaOrdenes.size()));
        botonSalir.addActionListener(e -> System.exit(0));
    }

    private void cargarArchivo() {
        String path = "src/ordenes.csv";
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String linea;
            br.readLine(); // Saltar encabezado
            int count = 0;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length >= 6) {
                    OrdenEnvio orden = new OrdenEnvio(
                        datos[0], 
                        Puerto.valueOf(datos[1].toUpperCase().replace(" ", "_")), 
                        Puerto.valueOf(datos[2].toUpperCase().replace(" ", "_")), 
                        Double.parseDouble(datos[3]), 
                        Double.parseDouble(datos[4]), 
                        TipoCarga.valueOf(datos[5].toUpperCase())
                    );
                    listaOrdenes.add(orden);
                    count++;
                }
            }
            JOptionPane.showMessageDialog(this, "Se cargaron " + count + " órdenes desde " + path);
        } catch (IOException | IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar archivo: " + ex.getMessage());
        }
    }

    private void procesarOrdenes() {
        if (listaOrdenes.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay órdenes para procesar. Cargue un archivo primero.");
            return;
        }

        ProcesadorOrdenes procesador = new ProcesadorOrdenes(new EstrategiaDryStandard());
        StringBuilder sb = new StringBuilder("Resultados del Proceso:\n");

        for (OrdenEnvio o : listaOrdenes) {
            
            switch (o.getTipoCarga()) {
                case DRY:
                    procesador.setEstrategia(new EstrategiaDryStandard());
                    break;
                case HAZMAT:
                    procesador.setEstrategia(new EstrategiaHazmat());
                    break;
                case REEFER:
                    procesador.setEstrategia(new EstrategiaReefer());
                    break;
            }
            
            ResultadoProceso res = procesador.procesarOrden(o);
            sb.append(res.toString()).append("\n");
        }

        JOptionPane.showMessageDialog(this, sb.toString());
    }
}