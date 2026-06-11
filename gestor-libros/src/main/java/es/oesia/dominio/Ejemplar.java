package es.oesia.dominio;

public class Ejemplar {
    private int numero;
    private EstadoEjemplar estado;
    private Libro libro;

    public Ejemplar(int numero, EstadoEjemplar estado, Libro libro) {
        this.numero = numero;
        this.estado = estado;
        this.libro = libro;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public EstadoEjemplar getEstado() {
        return estado;
    }

    public void setEstado(EstadoEjemplar estado) {
        this.estado = estado;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    @Override
    public String toString() {
        return "Ejemplar{" +
                "numero=" + numero +
                ", estado=" + estado +
                ", libro=" + libro.getTitulo() +
                '}';
    }
}
