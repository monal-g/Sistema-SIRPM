package modelo;

public class OrdenEnvio {
    private String id;
    private String origen;
    private String destino;
    private double peso;
    private double volumen;
    private TipoCarga tipoCarga;
    private boolean certificadoInternacional;
    private double temperaturaRequerida;
    private int diasTransito;

    public OrdenEnvio(String id, String origen, String destino, double peso, double volumen, TipoCarga tipoCarga) {
        this.id = id;
        this.origen = origen;
        this.destino = destino;
        this.peso = peso;
        this.volumen = volumen;
        this.tipoCarga = tipoCarga;
    }

    public String getId() { return id; }
    public String getOrigen() { return origen; }
    public String getDestino() { return destino; }
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
