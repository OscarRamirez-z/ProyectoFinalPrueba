public class Moto extends Vehiculo {
    private boolean tieneSidecar;

    // constructores
    public Moto(String placa, String marca, double precio, int cilindraje, boolean tieneSidecar) {
        super(placa, marca, precio, cilindraje);
        this.tieneSidecar = tieneSidecar;

        // para incrementar el impuesto por si tiene sidecar
        if (tieneSidecar) {
            setImpuestoCirculacion(getImpuestoCirculacion() * 1.1);
            setCuotaMesGaraje(getCuotaMesGaraje() * 1.5);
        }
    }

    // Getter y setter
    public boolean isTieneSidecar() {
        return tieneSidecar;
    }

    public void setTieneSidecar(boolean tieneSidecar) {
        this.tieneSidecar = tieneSidecar;
    }
}