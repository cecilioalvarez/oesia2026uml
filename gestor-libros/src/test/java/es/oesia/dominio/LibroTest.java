package es.oesia.dominio;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Pruebas para la clase Libro")
class LibroTest {

    @Test
    @DisplayName("Debe crear un libro con todos sus campos correctamente")
    void testConstruccionLibro() {
        String isbn = "978-1234567890";
        String titulo = "Clean Code";
        String autor = "Robert C. Martin";
        int paginas = 464;
        double precio = 45.99;

        Libro libro = new Libro(isbn, titulo, autor, paginas, precio);

        assertEquals(isbn, libro.getIsbn(), "El ISBN no coincide");
        assertEquals(titulo, libro.getTitulo(), "El título no coincide");
        assertEquals(autor, libro.getAutor(), "El autor no coincide");
        assertEquals(paginas, libro.getPaginas(), "El número de páginas no coincide");
        assertEquals(precio, libro.getPrecio(), 0.01, "El precio no coincide");
    }

    @Test
    @DisplayName("Debe calcular correctamente el IVA y el precio total")
    void testCalculoIva() {
        double precio = 100.0;
        Libro libro = new Libro("978-0987654321", "Test Driven Development", "Kent Beck", 300, precio);

        double ivaEsperado = 21.0;
        double precioConIvaEsperado = 121.0;

        double ivaActual = libro.calcularIva();
        double precioConIvaActual = libro.calcularPrecioConIva();

        assertEquals(ivaEsperado, ivaActual, 0.01, "El IVA no se calculó correctamente");
        assertEquals(precioConIvaEsperado, precioConIvaActual, 0.01, "El precio con IVA no es correcto");
    }
}
