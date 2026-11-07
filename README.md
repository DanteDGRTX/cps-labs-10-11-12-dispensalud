# Laboratorios CPS 10, 11 y 12 — DispenSalud

Proyectos Maven independientes para pruebas unitarias, acceso a datos e integración REST. Los proyectos 11 y 12 usan H2 en memoria para que sean autocontenidos.

## Proyectos

- `Lab10-cps_lab10`: JUnit 5, Log4j 2 y calculadora de dosis/stock para DispenSalud.
- `Lab11-petclinic_test`: seis pruebas de persistencia CRUD con `@SpringBootTest`, `JdbcTemplate` y H2. El repositorio docente `petclinic_test` no incluía los servicios/tablas completos solicitados, por eso el ejemplo autocontenido valida las operaciones directamente contra las tablas H2.
- `Lab12-petclinic_integration_test`: API REST CRUD sobre H2 con seis suites MockMvc y pruebas de controlador con Mockito.

Cada carpeta contiene su propio `README.md`, wrapper Maven y capturas de IntelliJ.
