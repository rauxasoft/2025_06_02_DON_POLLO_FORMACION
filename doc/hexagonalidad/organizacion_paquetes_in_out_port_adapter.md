# Organización de paquetes: ports y adapters

## Objetivo

Justificar la estructura recomendada de paquetes para proyectos en arquitectura hexagonal, comparando dos alternativas comunes.

---

## Alternativas evaluadas

### Opción A (recomendada):

```
com.tuempresa.tuproducto
 └── application
     ├── port
     │    ├── in
     │    └── out
     └── adapter
          ├── in
          └── out
```

### Opción B (menos recomendable):

```
com.tuempresa.tuproducto
 └── application
     ├── in
     │    ├── port
     │    └── adapter
     └── out
          ├── port
          └── adapter
```

---

## Ventajas de la Opción A (`port.in`, `port.out`, `adapter.in`, `adapter.out`)

### 1. Claridad semántica

* Agrupa primero por el **rol** (puerto o adaptador), y luego por su **dirección** (entrada o salida).
* Permite entender mejor la intención del código al navegar por el proyecto.

### 2. Escalabilidad

* Facilita la creación de subpaquetes sin ambigüedad:

```
adapter.in.web
adapter.out.jpa
adapter.out.rabbitmq
```

* Si se usara `in.adapter`, ¿dónde colocarías `web.config`? La estructura se vuelve menos clara.

### 3. Consistencia con referencias oficiales y prácticas modernas

* Coincide con estructuras vistas en:

  * Documentación de Alistair Cockburn (autor del patrón hexagonal)
  * Ejemplos de Spring Modulith, jMolecules y DDD aplicado en Java

### 4. Mejor experiencia en el IDE

* Las búsquedas rápidas y el autocompletado agrupan por concepto principal (`port`, `adapter`), facilitando el trabajo diario.

---

## Conclusión

✅ Se recomienda utilizar la estructura:

```
port.in
port.out
adapter.in
adapter.out
```

Agrupa por rol funcional primero y luego por dirección. Es más clara, mantenible y coherente con buenas prácticas.

---

## Nota

Esta organización no es obligatoria, pero sí altamente recomendable para proyectos medianos o grandes con enfoque DDD y arquitectura hexagonal.
