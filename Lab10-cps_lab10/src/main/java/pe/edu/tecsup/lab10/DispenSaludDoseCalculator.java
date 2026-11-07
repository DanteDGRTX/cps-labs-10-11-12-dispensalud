package pe.edu.tecsup.lab10;

/** Reglas de dominio pequeñas y deterministas para el prototipo PillBox Pro. */
public class DispenSaludDoseCalculator {
    public int calcularDosisDiarias(int intervaloHoras) {
        if (intervaloHoras <= 0 || intervaloHoras > 24)
            throw new IllegalArgumentException("El intervalo debe estar entre 1 y 24 horas");
        return 24 / intervaloHoras;
    }

    public int descontarStockCompartimento(int stockActual, int dosisEntregada) {
        if (dosisEntregada <= 0) throw new IllegalArgumentException("La dosis debe ser positiva");
        if (stockActual < dosisEntregada) throw new IllegalArgumentException("Stock insuficiente");
        return stockActual - dosisEntregada;
    }

    public boolean requiereAlertaPocoStock(int stockRestante, int umbralMinimo) {
        return stockRestante <= umbralMinimo;
    }

    public void validarCompartimentoDisponible(int numeroCompartimento, boolean ocupado) {
        if (numeroCompartimento < 1 || numeroCompartimento > 6)
            throw new IllegalArgumentException("El compartimento debe estar entre 1 y 6");
        if (ocupado) throw new IllegalStateException("El compartimento ya tiene un medicamento activo");
    }
}
