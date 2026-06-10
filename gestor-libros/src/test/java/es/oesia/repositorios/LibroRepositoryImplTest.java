package es.oesia.repositorios;

import es.oesia.dominio.Libro;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Pruebas para LibroRepositoryImpl")
class LibroRepositoryImplTest {

    private LibroRepository repositorio;

    @BeforeEach
    void setUp() {
        repositorio = new LibroRepositoryImpl();
    }

    @Test
    @DisplayName("Debe guardar un libro correctamente en el repositorio")
    void testGuardarLibro() {
        Libro libro = new Libro("978-1234567890", "Clean Code", "Robert C. Martin", 464, 45.99);

        repositorio.guardar(libro);

        assertTrue(repositorio.obtener("978-1234567890").isPresent(),
                "El libro debería estar en el repositorio después de guardarse");
        assertEquals(libro.getTitulo(), repositorio.obtener("978-1234567890").get().getTitulo(),
                "El libro guardado debe tener el mismo título");
    }

    @Test
    @DisplayName("Debe guardar múltiples libros correctamente")
    void testGuardarMultiplesLibros() {
        Libro libro1 = new Libro("978-0111111111", "TDD", "Kent Beck", 300, 39.99);
        Libro libro2 = new Libro("978-0222222222", "Refactoring", "Martin Fowler", 400, 55.99);
        Libro libro3 = new Libro("978-0333333333", "Design Patterns", "Gang of Four", 500, 60.99);

        repositorio.guardar(libro1);
        repositorio.guardar(libro2);
        repositorio.guardar(libro3);

        assertEquals(3, repositorio.obtenerTodos().size(),
                "Debería haber 3 libros en el repositorio");
    }

    @Test
    @DisplayName("No debe guardar un libro nulo")
    void testGuardarLibroNulo() {
        repositorio.guardar(null);

        assertEquals(0, repositorio.obtenerTodos().size(),
                "No debería haber libros después de intentar guardar nulo");
    }

    @Test
    @DisplayName("No debe guardar un libro sin ISBN")
    void testGuardarLibroSinISBN() {
        Libro libro = new Libro(null, "Sin ISBN", "Autor", 100, 10.0);

        repositorio.guardar(libro);

        assertEquals(0, repositorio.obtenerTodos().size(),
                "No debería guardar un libro sin ISBN");
    }

    @Test
    @DisplayName("Debe retornar lista vacía cuando no hay libros")
    void testObtenerTodosVacio() {
        List<Libro> libros = repositorio.obtenerTodos();

        assertNotNull(libros, "La lista no debe ser nula");
        assertTrue(libros.isEmpty(), "La lista debería estar vacía");
        assertEquals(0, libros.size(), "El tamaño debería ser 0");
    }

    @Test
    @DisplayName("Debe retornar todos los libros guardados")
    void testObtenerTodosConLibros() {
        Libro libro1 = new Libro("978-0111111111", "TDD", "Kent Beck", 300, 39.99);
        Libro libro2 = new Libro("978-0222222222", "Refactoring", "Martin Fowler", 400, 55.99);

        repositorio.guardar(libro1);
        repositorio.guardar(libro2);

        List<Libro> libros = repositorio.obtenerTodos();

        assertEquals(2, libros.size(), "Debería haber 2 libros");
        assertTrue(libros.stream().anyMatch(l -> l.getIsbn().equals("978-0111111111")),
                "Debería contener el libro con ISBN 978-0111111111");
        assertTrue(libros.stream().anyMatch(l -> l.getIsbn().equals("978-0222222222")),
                "Debería contener el libro con ISBN 978-0222222222");
    }

    @Test
    @DisplayName("Debe retornar una copia de la lista para evitar modificaciones externas")
    void testObtenerTodosRetornaUnacopia() {
        Libro libro = new Libro("978-0111111111", "TDD", "Kent Beck", 300, 39.99);
        repositorio.guardar(libro);

        List<Libro> libros1 = repositorio.obtenerTodos();
        List<Libro> libros2 = repositorio.obtenerTodos();

        assertNotSame(libros1, libros2,
                "Cada llamada debería retornar una instancia diferente de la lista");
        assertEquals(libros1.size(), libros2.size(),
                "Pero con el mismo contenido");
    }
}
