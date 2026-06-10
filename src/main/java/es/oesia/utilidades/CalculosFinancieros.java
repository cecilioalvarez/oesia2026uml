package es.oesia.utilidades;

public class CalculosFinancieros {
    private static final double IVA_STANDAR = 0.21;

    public static double calcularIva(double precio) {
        return precio * IVA_STANDAR;
    }

    public static double calcularPrecioConIva(double precio) {
        return precio + calcularIva(precio);
    }

    public static double calcularIvaConPorcentaje(double precio, double porcentaje) {
        return precio * porcentaje;
    }

    public static double calcularPrecioConIvaPersonalizado(double precio, double porcentaje) {
        return precio + calcularIvaConPorcentaje(precio, porcentaje);
    }
}
