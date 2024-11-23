import java.util.Scanner;

public class Prueba {
    public static void main(String[] args) {
        Parqueaderos red = new Parqueaderos(); 
        Scanner scanner = new Scanner(System.in);

        int opcion;
        do {
            System.out.println("\n--- Menú Principal ---");
            System.out.println("1. Crear garaje");
            System.out.println("2. Eliminar garaje");
            System.out.println("3. Actualizar información de garaje");
            System.out.println("4. Ingresar vehículo a un garaje");
            System.out.println("5. Retirar vehículo de un garaje");
            System.out.println("6. Generar informes");
            System.out.println("7. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1: // para crear un garaje
                    try {
                        System.out.println("Ingrese los datos del nuevo garaje:");
                        System.out.print("Departamento: ");
                        String departamento = scanner.nextLine();
                        System.out.print("Ciudad: ");
                        String ciudad = scanner.nextLine();
                        System.out.print("Dirección: ");
                        String direccion = scanner.nextLine();
                        System.out.print("Teléfono: ");
                        String telefono = scanner.nextLine();
                        System.out.print("Email: ");
                        String email = scanner.nextLine();
                        System.out.print("Nombre del administrador: ");
                        String administrador = scanner.nextLine();
                        System.out.print("Número de espacios: ");
                        int espacios = scanner.nextInt();

                        Garaje garaje = new Garaje(departamento, ciudad, direccion, telefono, email, administrador, espacios);
                        red.agregarGaraje(garaje);
                        System.out.println("Garaje creado exitosamente.");
                    } catch (Exception e) {
                        System.out.println("Error al crear el garaje: " + e.getMessage());
                    }
                    break;

                case 2: // para borrar un garaje
                    try {
                        System.out.print("Ingrese la dirección del garaje a eliminar: ");
                        String direccionEliminar = scanner.nextLine();
                        Garaje garajeEliminar = red.buscarGaraje(direccionEliminar);

                        if (garajeEliminar != null) {
                            red.eliminarGaraje(garajeEliminar);
                            System.out.println("Garaje eliminado correctamente.");
                        } else {
                            System.out.println("Garaje no encontrado.");
                        }
                    } catch (Exception e) {
                        System.out.println("Error al eliminar el garaje: " + e.getMessage());
                    }
                    break;

                case 3: // para mostrar informacion del garaje
                    System.out.print("Ingrese la dirección del garaje a actualizar: ");
                    String direccionActualizar = scanner.nextLine();
                    Garaje garajeActualizar = red.buscarGaraje(direccionActualizar);

                    if (garajeActualizar != null) {
                        System.out.println("Ingrese los nuevos datos (deje en blanco para mantener el valor actual):");
                        System.out.print("Nuevo teléfono: ");
                        String nuevoTelefono = scanner.nextLine();
                        System.out.print("Nuevo email: ");
                        String nuevoEmail = scanner.nextLine();

                        if (!nuevoTelefono.isEmpty()) {
                            garajeActualizar.setTelefono(nuevoTelefono);
                        }
                        if (!nuevoEmail.isEmpty()) {
                            garajeActualizar.setEmail(nuevoEmail);
                        }
                        System.out.println("Información del garaje actualizada correctamente.");
                    } else {
                        System.out.println("Garaje no encontrado.");
                    }
                    break;

                case 4: // ingresar un vehiculo a algun garaje
                    try {
                        System.out.print("Ingrese la dirección del garaje: ");
                        String direccionGaraje = scanner.nextLine();
                        Garaje garaje = red.buscarGaraje(direccionGaraje);

                        if (garaje == null) {
                            throw new ExcepcionGaraje("Garaje no encontrado.");
                        }

                        System.out.print("Tipo de vehículo (Auto, Moto, Camion): ");
                        String tipoVehiculo = scanner.nextLine();
                        System.out.print("Placa: ");
                        String placa = scanner.nextLine();

                        if (red.verificarVehiculoEnRed(placa)) {
                            throw new ExcepcionVehiculoYaRegistrado("El vehículo ya está registrado en otro garaje.");
                        }

                        System.out.print("Marca: ");
                        String marca = scanner.nextLine();
                        System.out.print("Precio: ");
                        double precio = scanner.nextDouble();
                        System.out.print("Cilindraje: ");
                        int cilindraje = scanner.nextInt();

                        Vehiculo vehiculo = switch (tipoVehiculo.toLowerCase()) {
                            case "moto" -> new Moto(placa, marca, precio, cilindraje, false);
                            case "auto" -> new Auto(placa, marca, precio, cilindraje, true, false);
                            case "camion" -> {
                                System.out.print("Número de ejes: ");
                                int ejes = scanner.nextInt();
                                System.out.print("Capacidad de carga (toneladas): ");
                                double capacidad = scanner.nextDouble();
                                yield new Camion(placa, marca, precio, cilindraje, ejes, "Sencillo", capacidad);
                            }
                            default -> throw new IllegalArgumentException("Tipo de vehículo inválido.");
                        };

                        garaje.agregarVehiculo(vehiculo);
                        System.out.println("Vehículo ingresado correctamente.");
                    } catch (Exception e) {
                        System.out.println("Error al ingresar el vehículo: " + e.getMessage());
                    }
                    break;

                case 5: // para retirar un vehiculo de algun garaje
                    try {
                        System.out.print("Ingrese la dirección del garaje: ");
                        String direccionGarajeRetirar = scanner.nextLine();
                        Garaje garajeRetirar = red.buscarGaraje(direccionGarajeRetirar);

                        if (garajeRetirar == null) {
                            throw new ExcepcionGaraje("Garaje no encontrado.");
                        }

                        System.out.print("Ingrese la matrícula del vehículo a retirar: ");
                        String matricula = scanner.nextLine();

                        garajeRetirar.retirarVehiculo(matricula);
                        System.out.println("Vehículo retirado correctamente.");
                    } catch (Exception e) {
                        System.out.println("Error al retirar el vehículo: " + e.getMessage()); 
                    }
                    break;

                case 6: // para generar los informes
                    System.out.println("1. Ocupación de todos los garajes");
                    System.out.println("2. Ocupación de un tipo de vehículo");
                    System.out.println("3. Recaudo mensual por garaje y total");
                    System.out.print("Seleccione una opción: ");
                    int informeOpcion = scanner.nextInt();

                    switch (informeOpcion) {
                        case 1 -> red.mostrarOcupacionPorGaraje();
                        case 2 -> {
                            System.out.print("Tipo de vehículo (Auto, Moto, Camion): ");
                            
                        }
                        case 3 -> red.mostrarRecaudoMensual(); 
                    }
                    break;

                case 7: // salir o acabarlo
                    System.out.println("Saliendo del programa.");
                    break;

                default:
                    System.out.println("Opción inválida.");
                    break;
            }
        } while (opcion != 7);
    }
}
