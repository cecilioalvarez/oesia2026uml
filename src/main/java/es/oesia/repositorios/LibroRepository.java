package es.oesia.repositorios;

import es.oesia.dominio.Libro;
import java.util.List;
import java.util.Optional;

public interface LibroRepository {
    void guardar(Libro libro);
    Optional<Libro> obtener(String isbn);
    List<Libro> obtenerTodos();
    void actualizar(Libro libro);
    void eliminar(String isbn);
}
