package es.oesia.formateadores;

import es.oesia.dominio.Libro;
import es.oesia.dominio.Ejemplar;

public class FormateadorLibro {

    public static String formatearLibro(Libro libro) {
        StringBuilder sb = new StringBuilder();
        sb.append("┌─────────────────────────────────────┐\n");
        sb.append("│          INFORMACIÓN DEL LIBRO      │\n");
        sb.append("├─────────────────────────────────────┤\n");
        sb.append(String.format("│ ISBN: %-29s │\n", libro.getIsbn()));
        sb.append(String.format("│ Título: %-26s │\n", libro.getTitulo()));
        sb.append(String.format("│ Autor: %-27s │\n", libro.getAutor()));
        sb.append(String.format("│ Páginas: %-24d │\n", libro.getPaginas()));
        sb.append(String.format("│ Precio: €%-24.2f │\n", libro.getPrecio()));
        sb.append(String.format("│ IVA (21%%): €%-20.2f │\n", libro.calcularIva()));
        sb.append(String.format("│ Total: €%-25.2f │\n", libro.calcularPrecioConIva()));
        sb.append("├─────────────────────────────────────┤\n");
        sb.append("│         EJEMPLARES DEL LIBRO        │\n");
        sb.append("├─────────────────────────────────────┤\n");

        if (libro.getEjemplares().isEmpty()) {
            sb.append("│ No hay ejemplares registrados.      │\n");
        } else {
            for (Ejemplar ejemplar : libro.getEjemplares()) {
                sb.append(String.format("│ Nº %d - Estado: %-21s │\n",
                    ejemplar.getNumero(),
                    ejemplar.getEstado().getDescripcion()));
            }
        }
        sb.append("└─────────────────────────────────────┘\n");
        return sb.toString();
    }

    public static String formatearLibroSimple(Libro libro) {
        return String.format(
            "%-15s | %-30s | %-20s | %d pgs | €%.2f",
            libro.getIsbn(),
            libro.getTitulo(),
            libro.getAutor(),
            libro.getPaginas(),
            libro.getPrecio()
        );
    }

    public static void imprimirLibro(Libro libro) {
        System.out.print(formatearLibro(libro));
    }
}
