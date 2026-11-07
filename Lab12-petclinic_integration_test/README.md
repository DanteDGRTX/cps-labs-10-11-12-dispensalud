# Laboratorio 12 Pruebas de Integración REST

Aplicación Spring Boot 3 con H2, MockMvc y Mockito. Incluye CRUD para veterinarios, propietarios, especialidades, visitas y tipos, además de asignaciones veterinario-especialidad. La API se expone en `/vets`, `/owners`, `/specialties`, `/visits`, `/types` y `/vet-specialties`.

## Ejecutar

```powershell
./mvnw clean test -Dspring.profiles.active=h2
./mvnw -Dtest=com.tecsup.petclinic.webs.VetControllerTest test -Dspring.profiles.active=h2
```

`application.yml` selecciona el perfil `h2`; el perfil mantiene una base en memoria para que cada ejecución sea reproducible. Owner incluye `consentimiento_informado` como extensión DispenSalud.
