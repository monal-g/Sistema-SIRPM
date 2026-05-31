package vista;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.*;

public class PantallaLogin extends JFrame {

    private JTextField txtUsuario;
    private JPasswordField txtContra;
    private JButton botonIngre;
    private JButton botonSalir;
    private JLabel jlMensaje;

    public PantallaLogin() {

        // Configuración de la ventana
        setTitle("Login");
        setSize(350, 220);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Usuario
        JLabel jlUsuario = new JLabel("Usuario:");
        jlUsuario.setBounds(30, 20, 100, 25);
        add(jlUsuario);

        txtUsuario = new JTextField();
        txtUsuario.setBounds(130, 20, 150, 25);
        add(txtUsuario);

        // Contraseña
        JLabel jlContra = new JLabel("Contraseña");
        jlContra.setBounds(30, 60, 100, 25);
        add(jlContra);

        txtContra = new JPasswordField();
        txtContra.setBounds(130, 60, 150, 25);
        add(txtContra);

        jlMensaje = new JLabel("");
        jlMensaje.setBounds(30, 150, 250, 25);
        add(jlMensaje);

        // Botones
        botonIngre = new JButton("Ingresar");
        botonIngre.setBounds(40, 110, 100, 30);
        botonSalir = new JButton("Salir");
        botonSalir.setBounds(170, 110, 100, 30);

        add(botonIngre);
        add(botonSalir);

        confiEventos();
    }

    private void confiEventos() {

        botonIngre.addActionListener(e -> validarLogin());
        botonSalir.addActionListener(e -> System.exit(0));
    }

    private void validarLogin() {

        String usuarioInput = txtUsuario.getText();
        String contraInput = new String(txtContra.getPassword());
        boolean loginExitoso = false;

        try (BufferedReader br = new BufferedReader(new FileReader("src/usuarios.csv"))) {
            String linea;
            br.readLine(); // saltar cabecera
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length >= 2) {
                    String userFile = datos[0];
                    String passFile = datos[1];

                    if (usuarioInput.equals(userFile) && contraInput.equals(passFile)) {
                        loginExitoso = true;
                        break;
                    }
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al leer archivo de usuarios");
        }

        if (loginExitoso) {
            jlMensaje.setText("Bienvenido al sistema SIRPM");
            abrirPantallaPrincipal();
        } else {
            jlMensaje.setText("Credenciales inválidas");
        }
    }

    private void abrirPantallaPrincipal() {

        JOptionPane.showMessageDialog(this, "Bienvenido al sistema");

        new PantallaPrincipal().setVisible(true);

        this.dispose();
    }
}