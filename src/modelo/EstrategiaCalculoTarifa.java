package modelo;
import java.util.List;

public interface EstrategiaCalculoTarifa {
    double calcularTarifa(OrdenEnvio orden);
    boolean validarOrden(OrdenEnvio orden);
    List<Puerto> obtenerRuta(OrdenEnvio orden);
    String getNombreEstrategia();
}
