package modelo;

import java.util.ArrayList;
import java.util.List;

public class EstrategiaReefer implements EstrategiaCalculoTarifa {
    private double consumoKwhDia = 5.0;

    @Override
    public double calcularTarifa(OrdenEnvio orden) {
        return (orden.getPeso() * 0.05) + calcularConsumoEnergetico(orden.getDiasTransito()); 
    }
    
    private double calcularConsumoEnergetico(int dias) {
        return dias * consumoKwhDia * 10; // 10 es un costo base por KWh
    }

    @Override
    public boolean validarOrden(OrdenEnvio orden) {
        return orden.getPeso() < 10000;
    }

    @Override
    public List<Puerto> obtenerRuta(OrdenEnvio orden) {
        List<Puerto> ruta = new ArrayList<>();
        ruta.add(orden.getOrigen());
        ruta.add(orden.getDestino());
        return ruta;
    }


    @Override
    public String getNombreEstrategia() {
        return "Carga Refrigerada (Reefer)";
    }
}
