# Control de transacciones en Arquitectura Hexagonal

## Objetivo

Explicar cómo gestionar las transacciones en un proyecto con arquitectura hexagonal sin romper la separación entre dominio e infraestructura.

---

## El problema

La anotación `@Transactional` proviene de **Spring Framework** (o JTA), y por tanto **es una preocupación técnica**. Incluirla directamente en los casos de uso (que forman parte de la capa de aplicación) introduce **acoplamiento con la infraestructura**.

---

## Opción A: Clase decoradora (recomendada en enfoque ortodoxo)

### Ventajas

* Mantiene los `UseCase` libres de dependencias técnicas.
* Facilita los tests unitarios del caso de uso sin framework.

### Ejemplo

```java
@Service
public class ClienteUseCasesTransactional implements ClienteInputPort {

    private final ClienteUseCases delegate;

    public ClienteUseCasesTransactional(ClienteUseCases delegate) {
        this.delegate = delegate;
    }

    @Override
    @Transactional
    public Long crearCliente(Cliente cliente) {
        return delegate.crearCliente(cliente);
    }

    // Otros métodos pueden delegarse igual, o solo los que necesiten transacción
}
```

El `ClienteUseCases` original **no lleva ninguna anotación**:

```java
public class ClienteUseCases implements ClienteInputPort {

    private final ClienteOutputPort clienteOutputPort;

    public ClienteUseCases(ClienteOutputPort clienteOutputPort) {
        this.clienteOutputPort = clienteOutputPort;
    }

    @Override
    public Long crearCliente(Cliente cliente) {
        // validaciones de negocio...
        return clienteOutputPort.crearCliente(cliente);
    }
}
```

---

## Opción B: Aceptar el uso de `@Transactional` en el UseCase (compromiso práctico)

### Ventajas

* Simplicidad.
* Alineado con prácticas comunes en proyectos Spring Boot.
* Facilita el onboarding de compañeros no acostumbrados a DDD puro.

### Desventajas

* Introduce acoplamiento leve con Spring en la capa de aplicación.

### Ejemplo

```java
@Service
public class ClienteUseCases implements ClienteInputPort {

    @Autowired
    private ClienteOutputPort clienteOutputPort;

    @Override
    @Transactional
    public Long crearCliente(Cliente cliente) {
        // validaciones de negocio...
        return clienteOutputPort.crearCliente(cliente);
    }
}
```

---

## Recomendación actual para DonPollo

En esta primera versión del proyecto **DonPollo**, se optará por la opción B, dejando constancia de que es un compromiso consciente. En futuras versiones o formaciones más avanzadas, se puede migrar a la opción A para lograr una separación total entre dominio e infraestructura.

---

## Nota

Nunca se debe anotar con `@Transactional` los adaptadores de salida (por ejemplo, los repositorios o mappers JPA). La transacción debe estar controlada desde el caso de uso.
