# Laboratorio 10 Pruebas Unitarias

Proyecto Maven del curso TECSUP, con la calculadora de la guía y reglas unitarias de DispenSalud.

## Requisitos

- JDK 17
- Maven 3.8 o superior

## Ejecutar las pruebas

```powershell
./mvnw clean test
```

`CalculatorTest` conserva las operaciones aritméticas y las trazas del ciclo JUnit 5. `DispenSaludDoseCalculatorTest` cubre intervalos, stock, umbral de alerta y compartimentos 1 a 6.
