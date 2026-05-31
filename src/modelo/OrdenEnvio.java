package modelo;

public class OrdenEnvio {
    private String id;
    private Puerto origen;
    private Puerto destino;
    private double peso;
    private double volumen;
    private TipoCarga tipoCarga;
    private boolean certificadoInternacional;
    private double temperaturaRequerida;
    private int diasTransito;

    public OrdenEnvio(String id, Puerto origen, Puerto destino, double peso, double volumen, TipoCarga tipoCarga, boolean certificadoInternacional, double temperaturaRequerida, int diasTransito) {
        this.id = id;
        this.origen = origen;
        this.destino = destino;
        this.peso = peso;
        this.volumen = volumen;
        this.tipoCarga = tipoCarga;
        this.certificadoInternacional = certificadoInternacional;
        this.temperaturaRequerida = temperaturaRequerida;
        this.diasTransito = diasTransito;
    }

    // Constructor parcial para compatibilidad
    public OrdenEnvio(String id, Puerto origen, Puerto destino, double peso, double volumen, TipoCarga tipoCarga) {
        this(id, origen, destino, peso, volumen, tipoCarga, false, 0, 0);
    }

    public String getId() { return id; }
    public Puerto getOrigen() { return origen; }
    public Puerto getDestino() { return destino; }
    public double getPeso() { return peso; }
    public double getVolumen() { return volumen; }
    public TipoCarga getTipoCarga() { return tipoCarga; }

    public boolean isCertificadoInternacional() { return certificadoInternacional; }
    public void setCertificadoInternacional(boolean certificadoInternacional) { this.certificadoInternacional = certificadoInternacional; }

    public double getTemperaturaRequerida() { return temperaturaRequerida; }
    public void setTemperaturaRequerida(double temperaturaRequerida) { this.temperaturaRequerida = temperaturaRequerida; }

    public int getDiasTransito() { return diasTransito; }
    public void setDiasTransito(int diasTransito) { this.diasTransito = diasTransito; }
}
