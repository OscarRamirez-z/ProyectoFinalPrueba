public class Camion extends Vehiculo {
    private int numeroEjes;
    private String tipoCamion;
    private double capacidadCarga; 

    // Constructores
    public Camion(String placa, String marca, double precio, int cilindraje, int numeroEjes, String tipoCamion, double capacidadCarga) {
        super(placa, marca, precio, cilindraje);

        if (tipoCamion.equalsIgnoreCase("Sencillo")) {
            if (numeroEjes != 2) {
                throw new IllegalArgumentException("Un camión sencillo debe tener exactamente 2 ejes.");
            }
            this.tipoCamion = "Sencillo";
            this.numeroEjes = 2;
            setCuotaMesGaraje(getCuotaMesGaraje() * 1.75); // Incremento del 75%
        } else if (tipoCamion.equalsIgnoreCase("Doble")) {
            if (numeroEjes < 3 || numeroEjes > 6) {
                throw new IllegalArgumentException("Un camión doble debe tener entre 3 y 6 ejes.");
            }
            this.tipoCamion = "Doble";
            this.numeroEjes = numeroEjes;
            setCuotaMesGaraje(getCuotaMesGaraje() * 2.25); // Incremento del 125%
        } else {
            throw new IllegalArgumentException("Tipo de camión no válido. Debe ser 'Sencillo' o 'Doble'.");
        }

        this.capacidadCarga = capacidadCarga;

        // Calcular impuesto de circulación
        double impuestoExtra = (capacidadCarga / 5) * 10; // $10 por cada 5 toneladas
        setImpuestoCirculacion(getPrecio() * 0.09 + impuestoExtra);
    }

    // Getters y Setters
    public int getNumeroEjes() {
        return numeroEjes;
    }

    public String getTipoCamion() {
        return tipoCamion;
    }

    public double getCapacidadCarga() {
        return capacidadCarga;
    }

    public void setNumeroEjes(int numeroEjes) {
        this.numeroEjes = numeroEjes;
    }

    public void setTipoCamion(String tipoCamion) {
        this.tipoCamion = tipoCamion;
    }

    public void setCapacidadCarga(double capacidadCarga) {
        this.capacidadCarga = capacidadCarga;
    }
}