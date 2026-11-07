package pe.edu.tecsup.lab10;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    private static final Logger log = LogManager.getLogger(CalculatorTest.class);
    private final Calculator calculator = new Calculator();

    @BeforeAll
    static void initAll() {
        Configurator.setRootLevel(org.apache.logging.log4j.Level.INFO);
        log.info("initAll()");
    }

    @AfterAll
    static void finishAll() { log.info("finishAll()"); }

    @BeforeEach
    void beforeTest() { log.info("beforeTest()"); }

    @AfterEach
    void afterTest() { log.info("afterTest()"); }

    @Test
    void testAdd() { assertEquals(7, calculator.add(4, 3)); }

    @Test
    void testSub() { assertEquals(1, calculator.sub(4, 3)); }

    @Test
    void testMul() { assertEquals(12, calculator.mul(4, 3)); }

    @Test
    void testDiv() { assertEquals(2, calculator.div(6, 3)); }

    @Test
    void testDivByZero() {
        assertThrows(ArithmeticException.class, () -> calculator.div(6, 0));
    }
}
