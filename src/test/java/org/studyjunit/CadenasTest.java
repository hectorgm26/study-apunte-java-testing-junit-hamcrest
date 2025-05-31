package org.studyjunit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;
import java.time.Month;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CadenasTest {

    private Cadenas cadenas;

    @BeforeEach
    public void setup() {
        cadenas = new Cadenas(); // Inicializa la variable cadena como un nuevo objeto de la clase Cadenas
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "    "})
    @NullAndEmptySource // Con esta anotacion, se le pasa un valor nulo y vacio a la prueba, haciendo dos tests
    public void testCadenaEsVacia(String cadena) {

        assertTrue(cadenas.esBlanco(cadena)); // se le pasa el valor del ValueSource, que se pasa como argumento al método, iterando por cada uno de los valores
    }

    // Recibe una lista de String, Y ASIGNARA CADA VALOR A SU RESPECTIVO PARAMETRO, Y CADA COMA SERA UN NUEVO TEST
    @ParameterizedTest(name = "Indice: {index}, la cadena {0} en mayusculas seria: {1}")
    // el {0} sera el primer argumento, y el {1} el segundo argumento, y con {arguments} se refiere a todos los argumentos de la iteracion de la prueba, e index se refiere al indice de la iteracion, que partira en 1
    @CsvSource({"java,JAVA", "Java,JAVA", "JAVa,JAVA"})
    @CsvSource(value = {"java:JAVA", "Java:JAVA", "JAVa:JAVA"}, delimiter = ':')
    // SI QUEREMOS CAMBIAR EL DELIMITADOR, usamos delimiter, y le pasamos el caracter que queremos usar como delimitador
    @CsvFileSource(resources = "/datos.csv", delimiter = ':', numLinesToSkip = 1)
    // si no le colocamos delimitador, por defecto sera una coma
    // SI QUEREMOS NO ENSUSIAR EL TEST PODEMOS USAR UN ARCHIVO CSV (creaarlo en resources), y lo pasamos como argumento al test
    // numLinesToSkip, nos permite saltar lineas del archivo, en este caso la primera linea que sera el encabezado del archivo
    public void testCadenaMayuscula(String valorEntrada, String valorEsperado) {

        assertEquals(valorEsperado, cadenas.pasarMayuscla(valorEntrada)); // Compara el valor real con el esperado, que se le pasa como argumento al método
    }

    // METHOD SOURCE
    @ParameterizedTest
    @MethodSource("generarDatosPrueba")
    // si no se le pasa metodo, buscara uno estatico con el mismo nombre, por lo que podriamos pasarle al argumento creado el mismo nombre del metodo
    public void testCadenaEsVacia2(String cadena, boolean valorEsperado) {
        assertEquals(valorEsperado, cadenas.esBlanco(cadena)); // se le pasa el valor del ValueSource, que se pasa como argumento al método, iterando por cada uno de los valores
    }

    // podriamos colocarle testCadenaEsVacia2 y dejar el methodSource sin nombre, y funcionaria igual
    private static Stream<Arguments> generarDatosPrueba() {
        return Stream.of(Arguments.of("", true),
                Arguments.of(null, true),
                Arguments.of("    ", true),
                Arguments.of(" No vacia ", false));
    }

    @ParameterizedTest
    @MethodSource("org.studyjunit.Prueba#provideBlankSource")
    // para importar un metodo publico se antepone el nombredelpaquete.nombredelaclase#el nombre del metodo a llamar
    public void testCadenaEsVacia3(String cadena) {
        assertTrue(cadenas.esBlanco(cadena));
    }

    @ParameterizedTest
    @MethodSource()
    public void testNombreCompleto(Persona persona, String resultadoEsperado) {
        assertEquals(resultadoEsperado, persona.getNombreCompleto());
    }

    private static Stream<Arguments> testNombreCompleto() {
        return Stream.of(Arguments.of(new Persona("Carlos", "Ruiz"), "Carlos Ruiz"),
                Arguments.of(new Persona("Ana", "Gomez"), "Ana Gomez"),
                Arguments.of(new Persona("Luis", ""), "Luis"));
    }

    static Stream<Arguments> arguments = Stream.of(Arguments.of(null, true),
            Arguments.of("", true),
            Arguments.of("", true),
            Arguments.of("    ", true),
            Arguments.of(" NO VACIA ", false));

    @ParameterizedTest
    // @VariableSource("arguments")
    @ArgumentsSource(CadenaEsBlancoArgumentProvider.class)
    public void testCadenaVacia4(String input, boolean valorEsperado) {
        assertEquals(valorEsperado, cadenas.esBlanco(input));
    }

    @ParameterizedTest
    @CsvSource({"APRIL", "JUNE", "SEPTEMBER", "NOVEMBER"})
    public void testMesDeberiaTener30Dias(Month mes) {
        assertEquals(30, mes.length(false));
    }

    @ParameterizedTest
    @CsvSource({"2024/06/08,2024", "2019/05/31,2019"})
    // se van a procesar las dos pruebas, y se descompondran los argumentos como LocalDate
    public void testGetYearWorksAsExpected(@ConvertWith(DateConverter.class) LocalDate input, int expectedYear) {
        assertEquals(expectedYear, input.getYear());
    }

    @ParameterizedTest
    @CsvSource({"Carlos,Ruiz,Carlos Ruiz", "Arlen,Mongallier,Arlen Mongallier"})
    public void testNombreCompletoPersona(ArgumentsAccessor accessor) {
        String nombre = accessor.getString(0); // Accede al primer argumento
        String apellido = accessor.getString(1); // Accede al segundo argumento
        String resultadoEsperado = accessor.getString(2); // Accede al tercer argumento
        // Ello en la primera vuelta del test, y se repetira en cada iteracion con los valores del csvSource

        // el accessor tendra metodo get que devuelve un objeto con indice de posicion, que sera la posicion de los argumentos del csvSource, que se delimitaran por la coma
        // (0) - Carlos, (1) - Ruiz, (2) - Carlos Ruiz
        // Tambien podemos usar un getDatoPrimitivo segun el dato que se requiera

        Persona persona = new Persona(nombre, apellido);
        assertEquals(resultadoEsperado, persona.getNombreCompleto());

    }

    @ParameterizedTest
    @CsvSource({"Carlos Ruiz,Carlos,Ruiz", "Arlen Mongallier,Arlen,Mongallier"})
    // Se deja siempre en los parametros las variables agregadas al final, cambiando el roden del csvSource
    public void testNombreCompletoPersonUsandoAgregados(String resultadoEsperado, @AggregateWith(PersonaAggregator.class) Persona persona) {

        assertEquals(resultadoEsperado, persona.getNombreCompleto());

    }


}
