package es.oesia.main;

import es.oesia.dominio.Libro;
import es.oesia.dominio.Ejemplar;
import es.oesia.dominio.EstadoEjemplar;
import es.oesia.repositorios.LibroRepository;
import es.oesia.repositorios.LibroRepositoryImpl;
import es.oesia.formateadores.FormateadorLibro;
import java.util.Scanner;
import java.util.Optional;

public class App {
    private static LibroRepository repositorio = new LibroRepositoryImpl();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean ejecutando = true;

        while (ejecutando) {
            mostrarMenu();
            int opcion = leerOpcion();

            switch (opcion) {
                case 1:
                    crearYGuardarLibro();
                    break;
                case 2:
                    listarLibros();
                    break;
                case 3:
                    actualizarLibro();
                    break;
                case 4:
                    eliminarLibro();
                    break;
                case 5:
                    agregarEjemplarALibro();
                    break;
                case 6:
                    ejecutando = false;
                    System.out.println("¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida. Intenta de nuevo.");
            }
            System.out.println();
        }

        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("=== GESTOR DE LIBROS ===");
        System.out.println("1. Crear nuevo libro");
        System.out.println("2. Listar todos los libros");
        System.out.println("3. Actualizar libro");
        System.out.println("4. Eliminar libro");
        System.out.println("5. Dar de alta ejemplar");
        System.out.println("6. Salir");
        System.out.print("Selecciona una opción: ");
    }

    private static int leerOpcion() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static void crearYGuardarLibro() {
        System.out.println("\n--- Crear nuevo libro ---");

        System.out.print("ISBN: ");
        String isbn = scanner.nextLine();

        System.out.print("Título: ");
        String titulo = scanner.nextLine();

        System.out.print("Autor: ");
        String autor = scanner.nextLine();

        System.out.print("Número de páginas: ");
        int paginas;
        try {
            paginas = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Número de páginas inválido.");
            return;
        }

        System.out.print("Precio: ");
        double precio;
        try {
            precio = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Precio inválido.");
            return;
        }

        Libro libro = new Libro(isbn, titulo, autor, paginas, precio);
        repositorio.guardar(libro);
        System.out.println("✓ Libro guardado exitosamente.");
    }

    private static void listarLibros() {
        System.out.println("\n--- Lista de libros ---");
        var libros = repositorio.obtenerTodos();

        if (libros.isEmpty()) {
            System.out.println("No hay libros registrados.");
        } else {
            libros.forEach(libro -> System.out.print(FormateadorLibro.formatearLibro(libro)));
        }
    }

    private static void actualizarLibro() {
        System.out.println("\n--- Actualizar libro ---");
        System.out.print("ISBN del libro a actualizar: ");
        String isbn = scanner.nextLine();

        Optional<Libro> libroOpt = repositorio.obtener(isbn);

        if (libroOpt.isEmpty()) {
            System.out.println("Libro no encontrado.");
            return;
        }

        Libro libroActual = libroOpt.get();
        System.out.println("Libro actual: " + libroActual);

        System.out.print("Nuevo título (Enter para mantener): ");
        String titulo = scanner.nextLine();
        if (!titulo.isEmpty()) {
            libroActual.setTitulo(titulo);
        }

        System.out.print("Nuevo autor (Enter para mantener): ");
        String autor = scanner.nextLine();
        if (!autor.isEmpty()) {
            libroActual.setAutor(autor);
        }

        System.out.print("Nuevas páginas (Enter para mantener): ");
        String paginasStr = scanner.nextLine();
        if (!paginasStr.isEmpty()) {
            try {
                libroActual.setPaginas(Integer.parseInt(paginasStr));
            } catch (NumberFormatException e) {
                System.out.println("Número inválido, se mantiene el valor anterior.");
            }
        }

        System.out.print("Nuevo precio (Enter para mantener): ");
        String precioStr = scanner.nextLine();
        if (!precioStr.isEmpty()) {
            try {
                libroActual.setPrecio(Double.parseDouble(precioStr));
            } catch (NumberFormatException e) {
                System.out.println("Precio inválido, se mantiene el valor anterior.");
            }
        }

        repositorio.actualizar(libroActual);
        System.out.println("✓ Libro actualizado exitosamente.");
    }

    private static void eliminarLibro() {
        System.out.println("\n--- Eliminar libro ---");
        System.out.print("ISBN del libro a eliminar: ");
        String isbn = scanner.nextLine();

        Optional<Libro> libroOpt = repositorio.obtener(isbn);

        if (libroOpt.isEmpty()) {
            System.out.println("Libro no encontrado.");
            return;
        }

        System.out.println("Libro a eliminar: " + libroOpt.get());
        System.out.print("¿Confirmar eliminación? (s/n): ");
        String confirmacion = scanner.nextLine();

        if (confirmacion.equalsIgnoreCase("s")) {
            repositorio.eliminar(isbn);
            System.out.println("✓ Libro eliminado exitosamente.");
        } else {
            System.out.println("Eliminación cancelada.");
        }
    }

    private static void agregarEjemplarALibro() {
        System.out.println("\n--- Dar de alta ejemplar ---");
        System.out.print("ISBN del libro: ");
        String isbn = scanner.nextLine();

        Optional<Libro> libroOpt = repositorio.obtener(isbn);

        if (libroOpt.isEmpty()) {
            System.out.println("Libro no encontrado.");
            return;
        }

        Libro libro = libroOpt.get();
        System.out.println("Libro seleccionado: " + libro.getTitulo());

        System.out.print("Número de ejemplar: ");
        int numero;
        try {
            numero = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Número de ejemplar inválido.");
            return;
        }

        System.out.println("Estados disponibles:");
        System.out.println("1. PEDIDO");
        System.out.println("2. STOCK");
        System.out.println("3. PRESTADO");
        System.out.println("4. PERDIDO");
        System.out.print("Selecciona el estado: ");

        int estadoOpcion;
        try {
            estadoOpcion = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Opción de estado inválida.");
            return;
        }

        EstadoEjemplar estado;
        switch (estadoOpcion) {
            case 1:
                estado = EstadoEjemplar.PEDIDO;
                break;
            case 2:
                estado = EstadoEjemplar.STOCK;
                break;
            case 3:
                estado = EstadoEjemplar.PRESTADO;
                break;
            case 4:
                estado = EstadoEjemplar.PERDIDO;
                break;
            default:
                System.out.println("Estado no válido.");
                return;
        }

        Ejemplar ejemplar = new Ejemplar(numero, estado, libro);
        libro.agregarEjemplar(ejemplar);
        repositorio.actualizar(libro);
        System.out.println("✓ Ejemplar dado de alta exitosamente.");
    }
}
