import java.util.ArrayList;

public class Garaje {
    private String departamento;
    private String ciudad;
    private String direccion;
    private String telefono;
    private String email;
    private String nombreAdministrador;
    private int numeroEspacios;
    private ArrayList<Vehiculo> espacios;

    // Constructores
    public Garaje(String departamento, String ciudad, String direccion, String telefono, String email, String nombreAdministrador, int numeroEspacios) {
        this.departamento = departamento;
        this.ciudad = ciudad;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.nombreAdministrador = nombreAdministrador;
        this.numeroEspacios = numeroEspacios;
        this.espacios = new ArrayList<>(numeroEspacios);
    }

    // Getters y setters
    public String getDepartamento() {
        return departamento;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getEmail() {
        return email;
    }

    public String getNombreAdministrador() {
        return nombreAdministrador;
    }

    public int getNumeroEspacios() {
        return numeroEspacios;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // metodo para calcular ingresos
    public double calcularIngresos() {
        double totalIngresos = 0;
        for (Vehiculo vehiculo : espacios) {
            if (vehiculo != null) {
                totalIngresos += vehiculo.getCuotaMesGaraje();
            }
        }
        return totalIngresos;
    }

    // metodo para buscar un vehículo por matrícula
    public Vehiculo buscarVehiculo(String placa) {
        for (Vehiculo vehiculo : espacios) {
            if (vehiculo != null && vehiculo.getPlaca().equalsIgnoreCase(placa)) {
                return vehiculo;
            }
        }
        return null; // Si no se encuentra la matricua
    }

    // metodo para calcular la ocupacion (si tiene espacio o esta lleno) actual del garaje
    public int calcularOcupacion() {
        int ocupados = 0;
        for (Vehiculo vehiculo : espacios) {
            if (vehiculo != null) {
                ocupados++;
            }
        }
        return ocupados;
    }

    // metodo para agregar un vehiculo a algun garaje
    public void agregarVehiculo(Vehiculo vehiculo) throws ExcepcionGaraje {
        if (vehiculo == null) {
            throw new ExcepcionGaraje("El vehículo no puede ser nulo.");
        }
        if (calcularOcupacion() >= numeroEspacios) {
            throw new ExcepcionGaraje("No hay espacios disponibles en este garaje.");
        }
        espacios.add(vehiculo);
    }

    // metodo para retirar un vehiculo
    public void retirarVehiculo(String placa) throws ExcepcionGaraje {
        Vehiculo vehiculo = buscarVehiculo(placa);
        if (vehiculo == null) {
            throw new ExcepcionGaraje("El vehículo con la placa " + placa + " no se encuentra en este garaje.");
        }
        espacios.remove(vehiculo);
    }
}
