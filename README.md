
# 📘 Apuntes de Testing Unitario en Java con JUnit y Hamcrest

Este repositorio es un compendio personal de apuntes y ejemplos prácticos sobre pruebas unitarias en Java, utilizando los frameworks JUnit y Hamcrest. Está diseñado como material de estudio y referencia rápida para desarrolladores que deseen profundizar en las capacidades de estas herramientas en el contexto del testing unitario.

## 📂 Estructura del Repositorio

- `/src`: Contiene ejemplos prácticos y casos de prueba implementados en Java.
- Archivos `.txt`: Documentación y notas sobre diversas funcionalidades y conceptos relacionados con JUnit y Hamcrest.

## 🧪 Contenido de los Apuntes

### 1. **Anotaciones en JUnit**
- `AnotacionesImportantes.txt`: Explica anotaciones clave como `@Test`, `@BeforeEach`, `@AfterEach`, `@BeforeAll`, `@AfterAll`, y `@Disabled`, detallando su propósito y uso en el ciclo de vida de las pruebas.

### 2. **Pruebas Parametrizadas**
- `Test Parametrizados.txt`: Introduce las pruebas parametrizadas en JUnit 5, permitiendo ejecutar el mismo test con diferentes conjuntos de datos.
- `AnotacionMethodSource.txt`: Describe el uso de `@MethodSource` para proporcionar argumentos a las pruebas.
- `ArgumentProvider.txt`: Detalla cómo implementar `ArgumentsProvider` para generar datos de prueba personalizados.
- `ArgumentsAccesor.txt`: Explica cómo acceder a los argumentos proporcionados en pruebas parametrizadas.
- `ArgumentsAggregator.txt`: Muestra cómo agrupar múltiples argumentos en un solo objeto utilizando `ArgumentsAggregator`.
- `ConversionArgumentos.txt`: Aborda la conversión automática de argumentos a tipos específicos en pruebas parametrizadas.
- `LiteralesCSV.txt`: Describe el uso de `@CsvSource` y `@CsvFileSource` para suministrar datos desde literales CSV o archivos externos.

### 3. **Asunciones y Condicionales**
- `Asunciones.txt`: Explica las asunciones (`assumeTrue`, `assumeFalse`, `assumingThat`) que permiten ejecutar pruebas condicionalmente según ciertas premisas.

### 4. **Organización y Etiquetado de Pruebas**
- `OrdenarTest.txt`: Detalla cómo ordenar la ejecución de pruebas utilizando `@TestMethodOrder`.
- `EtiquetandoTest.txt`: Explica el uso de `@Tag` para categorizar y filtrar pruebas durante su ejecución.

### 5. **Asserts y Matchers**
- `AssertsMetodos.txt`: Lista y describe los métodos de aserción proporcionados por JUnit para validar condiciones en las pruebas.
- `Hamcrest.txt`: Introduce el uso de Hamcrest para realizar aserciones más expresivas y legibles mediante matchers personalizados.

### 6. **Creación de Anotaciones Personalizadas**
- `CrearAnotaciones.txt`: Guía sobre cómo crear anotaciones personalizadas en JUnit para reutilizar configuraciones comunes en múltiples pruebas.

## 🔧 Requisitos

- **Java**: Versión 8 o superior.
- **JUnit 5**: Para aprovechar las funcionalidades modernas de pruebas unitarias.
- **Hamcrest**: Para utilizar matchers avanzados en las aserciones.

## 🚀 Ejecución de Pruebas

Para ejecutar las pruebas incluidas en el repositorio:

1. Clona el repositorio:

   ```bash
   git clone https://github.com/hectorgm26/study-apunte-java-testing-junit-hamcrest.git
   ```

2. Navega al directorio del proyecto:

   ```bash
   cd study-apunte-java-testing-junit-hamcrest
   ```

3. Compila y ejecuta las pruebas utilizando tu herramienta de construcción preferida (por ejemplo, Maven o Gradle).

## 📚 Recursos Adicionales

- [Documentación oficial de JUnit 5](https://junit.org/junit5/docs/current/user-guide/)
- [Guía de Hamcrest](http://hamcrest.org/JavaHamcrest/)
