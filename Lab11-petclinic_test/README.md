# Laboratorio 11: pruebas unitarias con base de datos

El proyecto incluye los seis casos `VetServiceTest`, `OwnerServiceTest`, `SpecialtyServiceTest`,
`VisitServiceTest`, `PetTypeServiceTest` y `VetSpecialtyServiceTest`. Los servicios consultan y
actualizan las tablas del esquema PetClinic con `JdbcTemplate`.

## Opción rápida: H2

Desde PowerShell, en la raíz del proyecto:

```powershell
.\mvnw.cmd '-Dtest=VetServiceTest,OwnerServiceTest,SpecialtyServiceTest' clean test '-Dspring.profiles.active=h2'
.\mvnw.cmd '-Dtest=VisitServiceTest,PetTypeServiceTest,VetSpecialtyServiceTest' clean test '-Dspring.profiles.active=h2'
```

Las corridas corresponden a los bloques del documento: 14 pruebas en los casos 1–3 y 13 en los
casos 4–6 (27 en total).

## MySQL local con Docker Desktop

1. Inicia Docker Desktop y espera a que indique que el motor está activo.
2. En la raíz del proyecto, ejecuta `docker compose up -d mysql` y espera a que el contenedor esté saludable.
3. Ejecuta las mismas dos corridas cambiando `h2` por `mysql`:

```powershell
.\mvnw.cmd '-Dtest=VetServiceTest,OwnerServiceTest,SpecialtyServiceTest' clean test '-Dspring.profiles.active=mysql'
.\mvnw.cmd '-Dtest=VisitServiceTest,PetTypeServiceTest,VetSpecialtyServiceTest' clean test '-Dspring.profiles.active=mysql'
```

La configuración local de MySQL está en `docker-compose.yml` y `src/main/resources/application-mysql.yml`.
Al finalizar, puedes apagar el contenedor con `docker compose down`.

**Estado de este equipo:** Docker Desktop informa que el hipervisor de Windows no está iniciado, por lo
que MySQL no puede arrancar aquí todavía. Las dos corridas H2 de arriba sí están verificadas. Para
presentar evidencia rotulada como MySQL, primero hay que resolver el estado del hipervisor y reiniciar
Windows; no uses las capturas H2 con una leyenda que diga MySQL.
