public class Auto extends Vehiculo {
    private boolean tieneRadio;
    private boolean tieneNavegador;

    // Constructores
    public Auto(String placa, String marca, double precio, int cilindraje, boolean tieneRadio, boolean tieneNavegador) {
        super(placa, marca, precio, cilindraje);
        this.tieneRadio = tieneRadio;
        this.tieneNavegador = tieneNavegador;

        // Ajustes de el parcial 2
        double impuestoExtra = 0;
        if (tieneRadio) {
            impuestoExtra += getPrecio() * 0.01;
        }
        if (tieneNavegador) {
            impuestoExtra += getPrecio() * 0.02;
        }
        setImpuestoCirculacion(getImpuestoCirculacion() + impuestoExtra);

        if (cilindraje > 2499) {
            setCuotaMesGaraje(getCuotaMesGaraje() * 1.2);
        }
    }

    // Getters y setters
    public boolean isTieneRadio() {
        return tieneRadio;
    }

    public void setTieneRadio(boolean tieneRadio) {
        this.tieneRadio = tieneRadio;
    }

    public boolean isTieneNavegador() {
        return tieneNavegador;
    }

    public void setTieneNavegador(boolean tieneNavegador) {
        this.tieneNavegador = tieneNavegador;
    }
}