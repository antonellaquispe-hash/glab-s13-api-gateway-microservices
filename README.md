# Laboratorio S13: API Gateway y microservicios

Repositorio ejecutable para el laboratorio de Service Discovery y API Gateway. Incluye Eureka Server, los cuatro servicios de negocio y Spring Cloud Gateway. Las respuestas de los servicios son datos de muestra en memoria.

## Servicios del repositorio

| Servicio | Puerto | Ruta directa | Ruta desde Gateway |
|---|---:|---|---|
| Eureka Server | 8761 | `/` | — |
| Gateway | 8080 | `/actuator/health` | — |
| Categorías | 8081 | `/api/categorias` | `http://localhost:8080/api/categorias` |
| Productos | 8082 | `/api/productos` | `http://localhost:8080/api/productos` |
| Clientes | 8083 | `/api/clientes` | `http://localhost:8080/api/clientes` |
| Pedidos | 8084 | `/api/pedidos` | `http://localhost:8080/api/pedidos` |

## Requisitos

- Java 17 o superior
- Maven 3.9+ (para ejecutar desde IDE o consola), o Docker Compose

## Ejecutar con Docker Compose

Desde la raíz del repositorio:

```powershell
docker compose up --build
```

Espera que los seis contenedores inicien. Eureka se abre en `http://localhost:8761`; los servicios aparecerán registrados por su nombre. Detén el entorno con `Ctrl+C` y `docker compose down`.

## Ejecutar con Maven

Abre seis terminales en la raíz y ejecuta, en este orden:

```powershell
mvn -pl eureka-server spring-boot:run
mvn -pl categoria-service spring-boot:run
mvn -pl producto-service spring-boot:run
mvn -pl cliente-service spring-boot:run
mvn -pl pedido-service spring-boot:run
mvn -pl gateway-service spring-boot:run
```

Verifica en `http://localhost:8761` que estén registrados `GATEWAY-SERVICE`, `CATEGORIA-SERVICE`, `PRODUCTO-SERVICE`, `CLIENTE-SERVICE` y `PEDIDO-SERVICE`.

## Pruebas Postman

Importa `postman/GLAB-S13-Gateway.postman_collection.json` y `postman/GLAB-S13-local.postman_environment.json`. Selecciona el entorno local y ejecuta la colección. Incluye chequeos de salud, rutas declaradas en Actuator y respuesta 2xx para los cuatro servicios pasando por el Gateway. También puedes consultar cada servicio directamente por su puerto para comparar la respuesta.

Los datos son ejemplos en memoria: se reinician al reiniciar cada servicio. No se requiere una base de datos para probar el enrutamiento.

## Conclusiones

1. El Gateway concentra el acceso a varios servicios detrás de un punto de entrada.
2. Eureka permite ubicar instancias por nombre lógico, sin fijar sus direcciones en las rutas.
3. Spring Cloud LoadBalancer resuelve los destinos `lb://` y puede distribuir llamadas entre instancias registradas.
4. Las rutas explícitas muestran con claridad qué API expone cada microservicio.
5. La disponibilidad de cada ruta depende de que Eureka y el servicio destino estén activos y registrados.

## Versiones

El documento menciona Spring Boot 3.5.15 en los metadatos y 3.5.3 en el POM de ejemplo. Este proyecto usa Spring Boot 3.5.15, Spring Cloud 2025.0.3 y Java 17.
