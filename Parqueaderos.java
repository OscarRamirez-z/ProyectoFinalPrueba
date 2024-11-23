import java.util.ArrayList;

public class Parqueaderos {
    private ArrayList<Garaje> listaGarajes;

    public Parqueaderos() {
        this.listaGarajes = new ArrayList<>();
    }

    // para agregar un garaje
    public void agregarGaraje(Garaje garaje) throws ExcepcionGaraje {
        if (garaje == null) {
            throw new ExcepcionGaraje("El garaje no puede ser nulo.");
        }
        listaGarajes.add(garaje);
    }

    // para eliminar un garaje
    public void eliminarGaraje(Garaje garaje) throws ExcepcionGaraje {
        if (!listaGarajes.remove(garaje)) {
            throw new ExcepcionGaraje("El garaje no existe en la red.");
        }
    }

    // para buscar un garaje por direccion
    public Garaje buscarGaraje(String direccion) {
        for (Garaje g : listaGarajes) {
            if (g.getDireccion().equalsIgnoreCase(direccion)) {
                return g;
            }
        }
        return null; // por si no lo encuentra
    }

    // para verificar si un vehiculo ya está registrado en algun otro garaje
    public boolean verificarVehiculoEnRed(String placa) {
        for (Garaje garaje : listaGarajes) {
            if (garaje.buscarVehiculo(placa) != null) {
                return true;
            }
        }
        return false;
    }

    // para ver la ocupación del garaje
    public void mostrarOcupacionPorGaraje() {
        for (Garaje garaje : listaGarajes) {
            System.out.println("Garaje en " + garaje.getDireccion() + ":");
            System.out.println("Ocupación: " + garaje.calcularOcupacion() + "/" + garaje.getNumeroEspacios());
        }
    }

    // para nostrar recaudo mensual por garaje
    public void mostrarRecaudoMensual() {
        double totalRecaudo = 0;
        for (Garaje garaje : listaGarajes) {
            double recaudoGaraje = garaje.calcularIngresos();
            totalRecaudo += recaudoGaraje;
            System.out.println("Recaudo mensual en garaje (" + garaje.getDireccion() + "): $" + recaudoGaraje);
        }
        System.out.println("Recaudo total: $" + totalRecaudo);
    }
} 