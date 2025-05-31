package org.studyjunit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Prueba {

    @Test
    @DisplayName("Probar las aserciones booleanas") // TEXTO QUE APARECERA AL EJECUTAR UN TEST
    public void asercionesBooleanas() {

        boolean flag = true;
        boolean flag2 = false;

        assertTrue(flag, "Flag deberia ser true"); // El mensaje se mostrara si la asercion falla

        assertFalse(flag2, "Flag deberia ser false");
    }

    @Test
    public void sercionesObjetosNull() {

        String valor = null;
        String valor2 = "Hola Mundo";

        assertNull(valor, "El objeto deberia ser null");

        assertNotNull(valor2, "El objeto no deberia ser null");
    }

    @Test
    public void asercionesEquals() {

        Persona personaEsperado = new Persona("Juan", "Pérez");
        Persona personaActual = new Persona("Juan", "Pérez");
        Persona personaActual2 = new Persona("Hector", "Pérez");

        assertEquals(personaEsperado, personaActual, "Ambas personas deberian ser iguales");
        // El metodo assertEquals compara el valor de los atributos de la clase Persona, no la referencia en memoria

        assertNotEquals(personaEsperado, personaActual2, "Ambas personas no deberian ser iguales");
    }

    @Test
    public void asercioneSameReference() {
        Persona personaEsperado = new Persona("Juan", "Pérez");
        Persona personaActual = new Persona("Juan", "Pérez");
        personaActual = personaEsperado; // Asignamos la referencia de personaEsperado a personaActual

        Persona personaActual2 = new Persona("Juan", "Pérez");

        assertSame(personaEsperado, personaActual, "Ambas deberian ser el mismo objeto");
        // ESTO CUMPLE EL EQUALS, PERO NO EL SAME, ya que no son dos instancias iguales

        assertNotSame(personaEsperado, personaActual2, "Ambas no deberian ser el mismo objeto");
    }

    @Test
    public void asercionesArray() {
        int[] arrayEsperado = {1, 2, 3};
        int[] arrayActual = {1, 2, 3};

        assertArrayEquals(arrayEsperado, arrayEsperado, "Los dos arrays deberian ser iguales");
    }

    @Test
    public void asercionesExcepciones() {
        Calculadora calculadora = new Calculadora();

        // El ejecutable se le pasa como una lambda, y se ejecuta cuando se llama al metodo assertThrows
        ArithmeticException exception = assertThrows(ArithmeticException.class, () -> calculadora.dividir(5, 0), "No se puede dividir entre cero");
        // el metodo devuelve la excepcion. Podemos guardar el resultado del assertThrows en una variable

        assertEquals("/ by zero", exception.getMessage());

        assertDoesNotThrow(() -> calculadora.dividir(5, 1));

    }

    @Test
    public void asercionesTimeout() {
        String mensaje = assertTimeoutPreemptively(Duration.ofMillis(1000), () -> { // tiempo maximo de espera 1 segundo
            Thread.sleep(900); // se hace una pausa de 500ms y despues se devuelve el mensaje
            return "Hola";
        });

        assertEquals("Hola", mensaje);
    }

    @Test
    public void asercionesAgrupadas() {
        Persona personaEsperada = new Persona("Juan", "Pérez");
        Persona personaActual = new Persona("Juan", "Pérez");

        // Podemos agrupar las aserciones en un bloque para validar todas a la vez, y asi que indique cual falla y cual no
        assertAll(() -> assertEquals(personaEsperada.getNombre(), personaActual.getNombre()),
                () -> assertEquals(personaEsperada.getApellido(), personaActual.getApellido())
        ); // el assertAll con el lambda permite agrupar las aserciones y si alguna falla, se mostrara el mensaje de error
    }

    // METODO PUBLICO DE AYUDA QUE SE IMPORTARA EN OTROS TESTS
    public static Stream<String> provideBlankSource() {
        return Stream.of("", null, "         ");
    }
}
