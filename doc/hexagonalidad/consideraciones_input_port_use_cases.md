# Diseño ortodoxo de InputPorts y UseCases en Arquitectura Hexagonal

## 1. Relación entre InputPorts y UseCases

* En una Arquitectura Hexagonal ortodoxa, la relación entre `InputPort` y `UseCase` es **1 a 1**.
* Cada `UseCase` implementa una única interfaz de entrada (`InputPort`).
* Cada `InputPort` define un único método público (generalmente llamado `execute()` por convención).

```java
public interface CrearPedidoInputPort {
    Pedido execute(CrearPedidoCommand command);
}

public class CrearPedidoUseCase implements CrearPedidoInputPort {
    public Pedido execute(CrearPedidoCommand command) {
        // lógica de dominio
    }
}
```

## 2. Sobre el método `execute()`

* Se utiliza ampliamente por convención, inspirado en el patrón Command.
* Su uso refuerza la uniformidad, especialmente útil en desarrollos genéricos.
* **Opcionalmente**, puede sustituirse por nombres más expresivos como `crear()`, `cancelar()`, etc., si se prioriza la legibilidad.

## 3. Atomización de los casos de uso

* Cada operación del negocio debe encapsularse como un caso de uso atómico.
* No se recomienda agrupar múltiples operaciones en una sola interfaz o clase de casos de uso.

```java
public interface ActualizarPedidoInputPort {
    void execute(ActualizarPedidoCommand command);
}

public class ActualizarPedidoUseCase implements ActualizarPedidoInputPort {
    public void execute(ActualizarPedidoCommand command) {
        // Validaciones y delegación al OutputPort
    }
}
```

## 4. Refactorización ortodoxa de un ejemplo real

### Antes:

```java
public interface PedidoInputPort {
    Long crearPedido(Pedido pedido);
    void actualizarPedido(Pedido pedido);
    List<Pedido> obtenerTodosLosPedidos();
    ... // muchos otros métodos
}

public class PedidoUseCases implements PedidoInputPort {
    // Implementación de todos los métodos en una sola clase
}
```

### Después:

```java
public interface CrearPedidoInputPort {
    Long execute(CrearPedidoCommand command);
}

public class CrearPedidoUseCase implements CrearPedidoInputPort {
    public Long execute(CrearPedidoCommand command) {
        // lógica de negocio para crear pedido
    }
}
```

## 5. Nombres de métodos: ¿son demasiado largos?

* Los nombres largos como `obtenerEstadisticaDeNumeroDePedidosParaCadaEstablecimientoEnUnPeriodo` son aceptables si expresan bien la intención.
* Puedes simplificar manteniendo la claridad: `obtenerEstadisticaPedidosPorEstablecimientoEnPeriodo`.

## 6. Uso de modelos como argumentos de entrada

* **Nunca usar directamente entidades del dominio como argumentos en los InputPorts.**

### Recomendación:

* Usar modelos de entrada específicos:

  * `CrearPedidoCommand`
  * `BuscarPedidosQuery`
* Así se desacopla la entrada de la lógica interna del dominio.

### Ejemplo:

```java
public interface CrearPedidoInputPort {
    Long execute(CrearPedidoCommand command);
}

public class CrearPedidoCommand {
    private Long idEstablecimiento;
    private List<Long> idProductos;
    private String observaciones;
    // Getters, Setters, Validaciones si procede
}
```

## 7. Ubicación de los modelos de entrada

### Estructura recomendada de paquetes:

```text
application
├── port
│   └── in
│       ├── CrearPedidoInputPort.java
│       └── BuscarPedidosInputPort.java
├── usecase
│   ├── CrearPedidoUseCase.java
│   └── BuscarPedidosUseCase.java
└── model
    ├── command
    │   └── CrearPedidoCommand.java
    └── query
        └── BuscarPedidosQuery.java
```

### Convención de nombres:

| Tipo      | Nombre sugerido                           |
| --------- | ----------------------------------------- |
| Escritura | `CrearXxxCommand`, `ActualizarXxxCommand` |
| Lectura   | `BuscarXxxQuery`, `ListarXxxQuery`        |

### DTOs de salida:

* Si devuelves vistas o resúmenes, crea DTOs en `application.model.response` o similar.

## 8. Conclusión

* Usa un `InputPort` por caso de uso.
* Nombra el método principal `execute()` para uniformidad o con verbos de dominio para claridad.
* No expongas modelos de dominio: usa `Command` y `Query` como modelos de entrada.
* Organiza tus archivos por casos de uso o por tipo (command/query).
* Favorece la claridad, el aislamiento y la testabilidad.
