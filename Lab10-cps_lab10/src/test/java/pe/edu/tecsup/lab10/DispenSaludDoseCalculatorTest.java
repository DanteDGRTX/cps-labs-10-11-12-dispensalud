package pe.edu.tecsup.lab10;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DispenSaludDoseCalculatorTest {
    private static final Logger log = LogManager.getLogger(DispenSaludDoseCalculatorTest.class);
    private final DispenSaludDoseCalculator calculator = new DispenSaludDoseCalculator();

    @Test
    void dosisCadaOchoHorasSonTresAlDia() {
        log.info("intervalo 8 h");
        assertEquals(3, calculator.calcularDosisDiarias(8));
    }

    @Test
    void rechazaIntervalosFueraDeRango() {
        assertAll(
            () -> assertThrows(IllegalArgumentException.class,
                () -> calculator.calcularDosisDiarias(0)),
            () -> assertThrows(IllegalArgumentException.class,
                () -> calculator.calcularDosisDiarias(25))
        );
    }

    @Test
    void descuentaUnaTabletaDelCompartimento() {
        int stockRestante = calculator.descontarStockCompartimento(
            6, 1);
        assertEquals(5, stockRestante);
    }

    @Test
    void rechazaDosisInvalidaOStockInsuficiente() {
        assertAll(
            () -> assertThrows(IllegalArgumentException.class,
                () -> calculator.descontarStockCompartimento(5, 0)),
            () -> assertThrows(IllegalArgumentException.class,
                () -> calculator.descontarStockCompartimento(0, 1))
        );
    }

    @Test
    void activaAlertaEnElUmbralIncluido() {
        boolean alertaEnUmbral = calculator
            .requiereAlertaPocoStock(3, 3);
        boolean alertaSobreUmbral = calculator
            .requiereAlertaPocoStock(4, 3);
        assertTrue(alertaEnUmbral);
        assertFalse(alertaSobreUmbral);
    }

    @Test
    void aceptaCompartimentosLibresDelUnoAlSeis() {
        for (int i = 1; i <= 6; i++) {
            final int numeroCompartimento = i;
            assertDoesNotThrow(() -> calculator
                .validarCompartimentoDisponible(numeroCompartimento, false));
        }
    }

    @Test
    void bloqueaCompartimentoOcupadoOFueraDeRango() {
        assertAll(
            () -> assertThrows(IllegalStateException.class,
                () -> calculator.validarCompartimentoDisponible(2, true)),
            () -> assertThrows(IllegalArgumentException.class,
                () -> calculator.validarCompartimentoDisponible(7, false))
        );
    }
}
