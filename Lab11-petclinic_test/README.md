# Laboratorio 11 Pruebas Unitarias con Base de Datos

Proyecto de ejemplo autocontenido con H2 en modo MySQL. Las seis clases `*ServiceTest` ejercitan persistencia CRUD y búsquedas mediante `JdbcTemplate` bajo `@SpringBootTest`; cada test se revierte al terminar.

## Ejecutar

```powershell
./mvnw clean test
./mvnw -Dtest=com.tecsup.petclinic.services.OwnerServiceTest clean test
```

La guía docente se basa en `petclinic_test`. El repositorio de referencia no contiene todos los servicios de las seis tablas; aquí se preparó un esquema H2 pequeño para que las evidencias no dependan de MySQL. Las equivalencias DispenSalud son reglas del proyecto integrador y deben conectarse a su backend Django por separado.


Repositorio público: https://github.com/DanteDGRTX/cps-labs-10-11-12-dispensalud
