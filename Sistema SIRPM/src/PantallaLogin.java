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

        String usuario = txtUsuario.getText();
        String contra = new String(txtContra.getPassword());

        // Usando contraseñas definidas
        if ((usuario.equals("admin") && contra.equals("admin123")) ||
            (usuario.equals("operador") && contra.equals("operador123"))) {

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