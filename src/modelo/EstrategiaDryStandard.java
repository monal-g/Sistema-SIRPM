package modelo;
import java.util.ArrayList;
import java.util.List;

public class EstrategiaDryStandard implements EstrategiaCalculoTarifa {
    private double tarifaBasePorM3 = 50.0;

    @Override
    public double calcularTarifa(OrdenEnvio orden) {
        return orden.getVolumen() * tarifaBasePorM3;
    }

    @Override
    public boolean validarOrden(OrdenEnvio orden) {
        return orden.getPeso() < 20000; // Ejemplo de validación
    }

    @Override
    public List<Puerto> obtenerRuta(OrdenEnvio orden) {
        List<Puerto> ruta = new ArrayList<>();
        // En un caso real buscaríamos los enums correspondientes
        return ruta;
    }

    @Override
    public String getNombreEstrategia() {
        return "Carga Seca Estándar";
    }
}
