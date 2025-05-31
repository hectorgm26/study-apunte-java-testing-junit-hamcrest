package org.studyjunit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.Month;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CalculadoraTest {

    private static Date fechaInicio;
    private static Date fechaFin;

    @BeforeAll
    public static void beforeAll() {
        fechaInicio = new Date();
        System.out.println("Inicio de la prueba: " + fechaInicio);
    }

    @AfterAll
    public static void afterAll() {
        fechaFin = new Date();
        System.out.println("Finalizando pruebas: " + fechaFin);
        System.out.println("Tiempo de prueba: " + (fechaFin.getTime() - fechaInicio.getTime()) + " milisegundos");
    }

    @AfterEach
    public void afterEach() {
        System.out.println("Ejecutado afterEach finalizando pruebas");
    }

    @BeforeEach
    public void beforeEach() {
        System.out.println("beforeEach Padre");
    }

    @Nested
    // Anotacion para indicar que es una clase anidada. Deben ser internas no estaticas. No admiten beforeAll ni afterAll, pero si beforeEach y afterEach
    // Las clases anidadas son utiles para agrupar pruebas relacionadas, y se ejecutan en el orden en que se declaran
    @DisplayName("Pruebas Unitarias de la Operacion Suma")
    class testOperacionSuma {

        Calculadora calculadora; // Declaramos la variable calculadora

        // ejecutara el metodo setup antes de cada una de las pruebas - Util para inicializar variables
        @BeforeEach
        public void setup() {
            calculadora = new Calculadora(); // se incializa la variable calculadora como un nuevo objeto de la clase Calculadora
            System.out.println("BeforeEach Suma");
        }

        @Test
        public void shouldTestSumar() {
            int resultado = calculadora.sumar(10, 5);
            // Verificar que el resultado del metodo es el correecto y esperado
            assertEquals(15, resultado);
        }

        @Test
        public void deberiaRetornarElMismoNumeroCuandoLeSumoCero() {
            int resultado = calculadora.sumar(5, 0);
            assertEquals(5, resultado);
        }
    }

    @Nested
    @DisplayName("Pruebas Unitarias de la Operacion Multiplicar")
    class testOperacionMultiplicar {

        Calculadora calculadora;

        // ejecutara el metodo setup antes de cada una de las pruebas - Util para inicializar variables
        @BeforeEach
        public void setup() {
            calculadora = new Calculadora(); // se incializa la variable calculadora como un nuevo objeto de la clase Calculadora
            System.out.println("BeforeEach Multiplicar");
        }

        @Test
        public void shouldReturnZeroWhenMultiplyZero() {
            int resultado = calculadora.multiplicar(5, 0);
            assertEquals(0, resultado);
        }

        @Test
        public void shouldReturnRightValueWhenMultiplyTwoNumbers() {
            int resultado = calculadora.multiplicar(5, 5);
            assertEquals(25, resultado);
        }
    }

    @Nested
    @DisplayName("Pruebas Unitarias de la Operacion Division")
    class testOperacionDivision {

        Calculadora calculadora;

        // ejecutara el metodo setup antes de cada una de las pruebas - Util para inicializar variables
        @BeforeEach
        public void setup() {
            calculadora = new Calculadora(); // se incializa la variable calculadora como un nuevo objeto de la clase Calculadora
            System.out.println("BeforeEach Dividir");
        }

        @Test
        public void deberiaFallarCuandoDividePorCero() {
            assertThrows(ArithmeticException.class, () -> {
                calculadora.dividir(5, 0);
            });
        }

        @Test
        public void deberiaRetornarLaDivisionEntreDosNumeros() {
            int resultado = calculadora.dividir(10, 5);
            assertEquals(2, resultado);
        }

        @Test
        public void deberiaRetornarElMismoNumeroAlDividirPorUno() {
            int resultado = calculadora.dividir(5, 1);
            assertEquals(5, resultado);
        }
    }


    //*************************** TEST PARAMETRIZADOS *******************************

    @Nested
    @DisplayName("Pruebas Unitarias de la Operacion es Impar")
    class TestEsImpar {

        Calculadora calculadora;

        @BeforeEach
        public void setup() {
            calculadora = new Calculadora();
        }

        // Test parametrizado - Ejecuta el mismo pero test con diferentes valores
        @ParameterizedTest(name = "Test {index} El valor {0} deberia ser impar ")
        // nombre del test, donde index sera la posicion, y 0 el argumento (aca solo tenemos un argumento, que es un solo array con valores)
        @ValueSource(ints = {1, 3, 25, -3, 99}) // valores con los cuales ejecutaremos el test
        public void testEsImpar(int valor) {
            assertTrue(calculadora.esImpar(valor), "El numero " + valor + " deberia ser impar");
            // El test iterara sobre todos los valores del array, y por cada uno de los valores invocara el test, pasando el valor
        }
    }


    @ParameterizedTest
    @EnumSource(value = Month.class, names = {"APRIL", "JUNE", "SEPTEMBER", "NOVEMBER"})
    // Al pasarle el enum, el test recorrera todos los valores del enum. Se debe pasar el valor al metodo igualmente
    // Podemos hacer que no se evaluen todos, si no un subconjunto con value y names
    public void testEnumerados(Month month) {

        int dias = month.length(false); // Este método devuelve la cantidad de días que tiene el mes, y el false indica que no es un año bisiesto.
        assertEquals(30, dias);
    }


    // AHORA QUE PASA SI QUEREMOS UTILIZAR TODOS LOS VALORES DEL ENUM, MENOS UNOS CUANTOS. EJEMPLO COGER TODOS LOS MESES, MENOS LOS QUE NO TIENEN 31 DIAS
    // Se usa luego de la lista en names, el mode = EnumSource.Mode.EXCLUDE, Y ASI SE EXCLUYEN DEL TEST LOS VALORES QUE ESTAN EN LA LISTA
    @ParameterizedTest
    @EnumSource(value = Month.class, names = {"FEBRUARY", "APRIL", "JUNE", "SEPTEMBER", "NOVEMBER"}, mode = EnumSource.Mode.EXCLUDE)
    // Al pasarle el enum, el test recorrera todos los valores del enum. Se debe pasar el valor al metodo igualmente
    // Podemos hacer que no se evaluen todos, si no un subconjunto con value y names
    public void testEnumerados2(Month month) {

        int dias = month.length(false); // Este método devuelve la cantidad de días que tiene el mes, y el false indica que no es un año bisiesto.
        assertEquals(31, dias);

        // Calcular los meses que terminan en "BER" con una expresion regular
        // @EnumSource(value = Month.class, names = {".+BER"}, mode = EnumSource.Mode.MATCH_ANY)
    }


    @Timeout(1) // Si este metodo tarda mas de 1 segundo en ejecutarse (o lo que se le pase), el test fallara
    @Test
    public void testShouldTakenLongTime() throws InterruptedException {
        Thread.sleep(800); // 1500 milisegundos = 1 segundo y medio
    }

}
