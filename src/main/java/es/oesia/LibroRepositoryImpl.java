package es.oesia;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class LibroRepositoryImpl implements LibroRepository {
    private Map<String, Libro> libros = new HashMap<>();

    @Override
    public void guardar(Libro libro) {
        if (libro != null && libro.getIsbn() != null) {
            libros.put(libro.getIsbn(), libro);
        }
    }

    @Override
    public Optional<Libro> obtener(String isbn) {
        return Optional.ofNullable(libros.get(isbn));
    }

    @Override
    public List<Libro> obtenerTodos() {
        return List.copyOf(libros.values());
    }

    @Override
    public void actualizar(Libro libro) {
        if (libro != null && libro.getIsbn() != null && libros.containsKey(libro.getIsbn())) {
            libros.put(libro.getIsbn(), libro);
        }
    }

    @Override
    public void eliminar(String isbn) {
        libros.remove(isbn);
    }
}
