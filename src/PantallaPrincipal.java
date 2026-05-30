import javax.swing.*;
import java.awt.*;

public class PantallaPrincipal extends JFrame {


    private JLabel titulo;
    private JButton botonRegistrar;
    private JButton botonProcesar;
    private JButton botonLimpiar;
    private JButton botonCargar;
    private JButton botonResultados;
    private JButton botonSalir;
    private JPanel jpBotones;

    public PantallaPrincipal() {
        
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
        botonProcesar.addActionListener(e -> JOptionPane.showMessageDialog(this, "En desarrollo"));
        botonLimpiar.addActionListener(e -> JOptionPane.showMessageDialog(this, "En desarrollo"));
        botonCargar.addActionListener(e -> JOptionPane.showMessageDialog(this, "En desarrollo"));
        botonResultados.addActionListener(e -> JOptionPane.showMessageDialog(this, "En desarrollo"));
        botonSalir.addActionListener(e -> System.exit(0));
    }
}