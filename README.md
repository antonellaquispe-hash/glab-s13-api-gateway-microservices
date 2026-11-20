# Laboratorio S13: API Gateway en microservicios

Implementación del laboratorio de Spring Cloud Gateway y Eureka. El Gateway funciona como entrada única y publica las rutas de categorías, productos, clientes y pedidos.

## Requisitos

- Java 17 o superior
- Maven 3.9+
- Eureka Server disponible en `http://localhost:8761/eureka`
- Los cuatro microservicios registrados con los nombres `categoria-service`, `producto-service`, `cliente-service` y `pedido-service`

## Ejecutar

1. Inicia Eureka y los microservicios previos de categorías y productos.
2. Inicia `cliente-service` y `pedido-service` (la actividad del documento).
3. En esta carpeta ejecuta `mvn spring-boot:run`.
4. Comprueba el registro en Eureka y prueba el puerto 8080.

El Gateway no implementa la lógica de negocio de los servicios previos. Las respuestas de las rutas requieren que esos servicios estén levantados y registrados en Eureka.

## Rutas

| Servicio | Ruta pública | Destino Eureka |
|---|---|---|
| Categorías | `/api/categorias/**` | `lb://categoria-service` |
| Productos | `/api/productos/**` | `lb://producto-service` |
| Clientes | `/api/clientes/**` | `lb://cliente-service` |
| Pedidos | `/api/pedidos/**` | `lb://pedido-service` |

Se habilita también el descubrimiento automático (`/{serviceId}/**`). En las rutas explícitas no se reescriben prefijos para conservar el contrato esperado por cada servicio.

## Pruebas Postman

Importa `postman/GLAB-S13-Gateway.postman_collection.json` y opcionalmente `postman/GLAB-S13-local.postman_environment.json`. Ejecuta las cuatro peticiones con los servicios registrados. Cada petición verifica el estado HTTP, que la respuesta no sea 5xx y el tiempo de respuesta. Como los contratos y datos de los servicios previos no fueron incluidos en el material recibido, las pruebas no presuponen cuerpos ni códigos de creación específicos.

Actuator expone `/actuator/health` y `/actuator/gateway/routes` para verificar estado y rutas configuradas.

## Conclusiones

1. El API Gateway concentra el acceso a varios servicios detrás de un punto de entrada.
2. Eureka permite ubicar instancias por nombre lógico, evitando fijar sus direcciones en las rutas.
3. Spring Cloud LoadBalancer resuelve `lb://` y permite distribuir llamadas entre instancias registradas.
4. Mantener rutas explícitas facilita identificar y administrar qué API expone cada microservicio.
5. El Gateway simplifica la integración del cliente; la disponibilidad final depende de que Eureka y los servicios destino estén activos.

## Nota de versiones

El documento menciona Spring Boot 3.5.15 en los metadatos y Spring Boot 3.5.3 en el `pom.xml` de ejemplo. Este proyecto usa Boot 3.5.15 con Spring Cloud 2025.0.3, que corresponde a la línea Boot 3.5. El proyecto conserva Java 17 solicitado en la guía.
