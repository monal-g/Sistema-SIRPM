package controlador;

import java.util.ArrayList;
import java.util.List;
import modelo.*;

public class ProcesadorOrdenes {
    private EstrategiaCalculoTarifa estrategia;
    private List<ResultadoProceso> historial;

    public ProcesadorOrdenes(EstrategiaCalculoTarifa e) {
        this.estrategia = e;
        this.historial = new ArrayList<>();
    }

    public void setEstrategia(EstrategiaCalculoTarifa e) {
        this.estrategia = e;
    }

    public ResultadoProceso procesarOrden(OrdenEnvio o) {
        if (estrategia.validarOrden(o)) {
            double tarifa = estrategia.calcularTarifa(o);
            List<Puerto> ruta = estrategia.obtenerRuta(o);
            ResultadoProceso res = new ResultadoProceso(o.getId(), tarifa, ruta, true, "Procesado con éxito");
            registrarResultado(res);
            return res;
        } else {
            return new ResultadoProceso(o.getId(), 0, null, false, "Orden no válida para esta estrategia");
        }
    }


    protected void registrarResultado(ResultadoProceso r) {
        historial.add(r);
    }
}
