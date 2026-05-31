package vista;

import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.*;
import modelo.*;

public class RegistroOrden extends JFrame {

    private JTextField txtId, txtPeso, txtVolumen;
    private JComboBox<Puerto> cbOrigen, cbDestino;
    private JComboBox<TipoCarga> cbTipoCarga;
    private JButton btnGuardar, btnCancelar;

    public RegistroOrden() {
        setTitle("Registrar Nueva Orden");
        setSize(400, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(7, 2, 10, 10));

        add(new JLabel(" ID Orden:"));
        txtId = new JTextField();
        add(txtId);

        add(new JLabel(" Origen:"));
        cbOrigen = new JComboBox<>(Puerto.values());
        add(cbOrigen);

        add(new JLabel(" Destino:"));
        cbDestino = new JComboBox<>(Puerto.values());
        add(cbDestino);

        add(new JLabel(" Peso (kg):"));
        txtPeso = new JTextField();
        add(txtPeso);

        add(new JLabel(" Volumen (m3):"));
        txtVolumen = new JTextField();
        add(txtVolumen);

        add(new JLabel(" Tipo de Carga:"));
        cbTipoCarga = new JComboBox<>(TipoCarga.values());
        add(cbTipoCarga);

        btnGuardar = new JButton("Guardar");
        btnCancelar = new JButton("Cancelar");

        add(btnGuardar);
        add(btnCancelar);

        btnCancelar.addActionListener(e -> dispose());
        btnGuardar.addActionListener(e -> guardarOrden());
    }

    private void guardarOrden() {
        try {
            String id = txtId.getText();
            Puerto origen = (Puerto) cbOrigen.getSelectedItem();
            Puerto destino = (Puerto) cbDestino.getSelectedItem();
            double peso = Double.parseDouble(txtPeso.getText());
            double volumen = Double.parseDouble(txtVolumen.getText());
            TipoCarga tipoCarga = (TipoCarga) cbTipoCarga.getSelectedItem();

            if (peso <= 0 || volumen <= 0) {
                JOptionPane.showMessageDialog(this, "Peso y volumen deben ser positivos.");
                return;
            }

            if (id.isEmpty()) {
                JOptionPane.showMessageDialog(this, "El ID no puede estar vacío.");
                return;
            }

            String strOrigen = origen.name().replace("_", " ");
            String strDestino = destino.name().replace("_", " ");
            
            // Asumimos un formato de string por ejemplo: 12000.0 sin decimales extraños locale specific
            String strPeso = String.valueOf(peso);
            String strVol = String.valueOf(volumen);

            try (PrintWriter pw = new PrintWriter(new FileWriter("src/ordenes.csv", true))) {
                pw.println(String.format("%s,%s,%s,%s,%s,%s,false,0,0", id, strOrigen, strDestino, strPeso, strVol, tipoCarga.name()));
            }

            JOptionPane.showMessageDialog(this, "Orden guardada correctamente.");
            dispose();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Verifique los valores numéricos (Peso/Volumen).");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar la orden: " + ex.getMessage());
        }
    }
}

