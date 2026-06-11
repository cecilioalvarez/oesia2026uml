package es.oesia.dominio;

public enum EstadoEjemplar {
    PEDIDO("Pedido"),
    STOCK("Stock"),
    PRESTADO("Prestado"),
    PERDIDO("Perdido");

    private final String descripcion;

    EstadoEjemplar(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
