package modelo;
import java.util.List;

public class ResultadoProceso {
    private String idOrden;
    private double tarifaFinal;
    private List<Puerto> ruta;
    private boolean exitoso;
    private String mensaje;

    public ResultadoProceso(String idOrden, double tarifaFinal, List<Puerto> ruta, boolean exitoso, String mensaje) {
        this.idOrden = idOrden;
        this.tarifaFinal = tarifaFinal;
        this.ruta = ruta;
        this.exitoso = exitoso;
        this.mensaje = mensaje;
    }

    @Override
    public String toString() {
        return "Orden " + idOrden + " | Tarifa: $" + (exitoso ? tarifaFinal : "N/A") + " | Exitoso: " + exitoso + (exitoso ? "" : " | Motivo: " + mensaje);
    }
}
