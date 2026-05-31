package modelo;

import java.util.ArrayList;
import java.util.List;

public class EstrategiaHazmat implements EstrategiaCalculoTarifa {
    private double factorSeguro = 1.5;
    private List<Puerto> puertosHabilitados;

    public EstrategiaHazmat() {
        puertosHabilitados = new ArrayList<>();
        puertosHabilitados.add(Puerto.SHANGHAI);
        puertosHabilitados.add(Puerto.LOS_ANGELES);
        puertosHabilitados.add(Puerto.VALPARAISO);
    }

    @Override
    public double calcularTarifa(OrdenEnvio orden) {
        return (orden.getPeso() * 0.1) * factorSeguro;
    }

    @Override
    public boolean validarOrden(OrdenEnvio orden) {
        // Validar si el puerto de destino está habilitado para Hazmat
        return puertosHabilitados.contains(orden.getDestino()) && validarCertificados(orden);
    }

    private boolean validarCertificados(OrdenEnvio orden) {
        return orden.isCertificadoInternacional();
    }

    @Override
    public List<Puerto> obtenerRuta(OrdenEnvio orden) {
        List<Puerto> ruta = new ArrayList<>();
        ruta.add(orden.getOrigen());
        ruta.add(orden.getDestino()); // Ruta directa por defecto en nuestro modelo simple
        return ruta;
    }


    @Override
    public String getNombreEstrategia() {
        return "Carga Peligrosa (Hazmat)";
    }
}
