package es.oesia.dominio;

import es.oesia.utilidades.CalculosFinancieros;
import java.util.ArrayList;
import java.util.List;

public class Libro {
    private String isbn;
    private String titulo;
    private String autor;
    private int paginas;
    private double precio;
    private List<Ejemplar> ejemplares;

    public Libro(String isbn, String titulo, String autor, int paginas, double precio) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.paginas = paginas;
        this.precio = precio;
        this.ejemplares = new ArrayList<>();
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getPaginas() {
        return paginas;
    }

    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double calcularIva() {
        return CalculosFinancieros.calcularIva(precio);
    }

    public double calcularPrecioConIva() {
        return CalculosFinancieros.calcularPrecioConIva(precio);
    }

    public List<Ejemplar> getEjemplares() {
        return ejemplares;
    }

    public void setEjemplares(List<Ejemplar> ejemplares) {
        this.ejemplares = ejemplares;
    }

    public void agregarEjemplar(Ejemplar ejemplar) {
        this.ejemplares.add(ejemplar);
    }

    public void eliminarEjemplar(Ejemplar ejemplar) {
        this.ejemplares.remove(ejemplar);
    }

    public int getTotalEjemplares() {
        return this.ejemplares.size();
    }

    @Override
    public String toString() {
        return "Libro{" +
                "isbn='" + isbn + '\'' +
                ", titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", paginas=" + paginas +
                ", precio=" + precio +
                '}';
    }
}
