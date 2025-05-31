package org.studyjunit;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.aMapWithSize;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.any;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.arrayContaining;
import static org.hamcrest.Matchers.arrayContainingInAnyOrder;
import static org.hamcrest.Matchers.blankOrNullString;
import static org.hamcrest.Matchers.blankString;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.containsStringIgnoringCase;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.equalToCompressingWhiteSpace;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItemInArray;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.hasToString;
import static org.hamcrest.Matchers.hasValue;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isA;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.isEmptyString;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.Matchers.sameInstance;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.hamcrest.Matchers.startsWith;
import static org.hamcrest.Matchers.stringContainsInOrder;
import static org.hamcrest.Matchers.typeCompatibleWith;

public class EjemploHamcrest {

    @Test
    public void ejemplos() {
        // Comparar dos cadenas, independientemente de mayúsculas y minúsculas
        String cadena = "Java";
        String cadena2 = "JAVA";

        // matcher de cadena - assertThat lo que hara es que evaluara la cadena como primer parametro, con la seconda cadena como matcher
        assertThat(cadena, equalToIgnoringCase(cadena2));
        // Sin Hamcrest, esta validacion se tendria que hacer manualmente


        // Podemos hacer aserciones sobre clases y objetos
        Persona persona = new Persona("Hector", "Gonzalez");
        String expected = persona.toString();

        assertThat(persona, hasToString(expected));

        // Podemos hacer aserciones sobre la herencia, ejemplo ver si una clase es compatible con otra
        assertThat(ArrayList.class, typeCompatibleWith(List.class));
    }

    // Los matchers de tipo bean son relacionados a los objetos, y se utilizan para validar propiedades de un objeto
    @Test
    public void ejemploMatchersTipoBean() {
        Persona actual = new Persona("Hector", "Gonzalez");

        Persona expected = new Persona("Hector", "Gonzalez");

        // En junit seria en assertEquals, pero con matchers de hamcrest seria:
        assertThat(actual, is(expected));

        assertThat(actual, hasProperty("nombre"));
        // Asertar que actual tiene una propiedad llamada "nombre" y que es igual a "Hector"
        assertThat(actual, hasProperty("nombre", equalTo("Hector")));

        assertThat(actual, any(Persona.class)); // comprueba hasta herencia

        assertThat(actual, isA(Persona.class)); // comprueba que es una instancia de Persona

        assertThat(actual, not(Integer.class)); // comprueba que no es una instancia de Integer

        assertThat(actual, notNullValue());

        assertThat(null, is(nullValue()));

        assertThat(null, sameInstance(expected));

        assertThat(actual, samePropertyValuesAs(expected)); // compara las propiedades del objeto actual con las del objeto esperado

    }

    @Test
    public void ejemploMatchersColecciones() {

        String[] colores = {"verde", "azul", "amarillo"};

        // asertar que colores es un array de tamaño 3 (lo mide desde el 1, no desde el 0 como comunmente se hace)
        assertThat(colores, Matchers.arrayWithSize(3));

        assertThat(colores, hasItemInArray("azul"));

        assertThat(colores, arrayContainingInAnyOrder("verde", "azul", "amarillo"));

        assertThat(colores, arrayContaining("verde", "azul", "amarillo"));

        // el metodo Arrays.asList convierte un array en una lista
        List<String> listaColores = Arrays.asList(colores);

        assertThat(new ArrayList<String>(), Matchers.empty());

        assertThat(listaColores, hasSize(3));

        assertThat(listaColores, hasItem("azul"));

        assertThat(listaColores, contains("verde", "azul", "amarillo"));

        assertThat(listaColores, containsInAnyOrder("amarillo", "azul", "verde"));


        Map<String, Integer> mapColores = new HashMap<>();

        // assertThat(mapColores, anEmptyMap());

        mapColores.put("verde", 2);
        mapColores.put("azul", 50);
        assertThat(mapColores, aMapWithSize(2));

        assertThat(mapColores, aMapWithSize(Matchers.greaterThan(1)));

        assertThat(mapColores, hasKey("verde"));

        assertThat(mapColores, hasValue("50"));

        assertThat(mapColores, hasEntry("verde", 2));
    }

    @Test
    public void ejemploMatcherNumeros() {

        int actual = 8;
        assertThat(actual, greaterThan(5));

        assertThat(actual, greaterThanOrEqualTo(6));

        assertThat(actual, lessThan(10));

        assertThat(actual, lessThanOrEqualTo(8));

        assertThat("A", lessThan("B")); // compara cadenas mediante el orden lexicográfico a traves del CompareTo

        // Decir que un valor esta dentro de un determinado rango
        assertThat(1.2, closeTo(1.0, 0.5)); // 1.2 esta dentro del rango de 1.0 + o - 0.5
    }

    @Test
    public void ejemploMatchersStrings() {

        assertThat("", isEmptyString());
        assertThat("", isEmptyOrNullString());

        assertThat("    ", blankString());
        assertThat("    ", blankOrNullString());

        assertThat("Hola", equalTo("Hola"));
        assertThat("Hola", equalToIgnoringCase("hola"));

        // Comprueba si una cadena es igual a otra cadena específica, ignorando los espacios en blanco al principio y al final de la cadena
        assertThat("    hola     ", equalToCompressingWhiteSpace("hola"));

        String saludo = "Hola todo el mundo";
        assertThat(saludo, stringContainsInOrder("Hola", "todo", "mundo"));

        assertThat(saludo, containsString("todo"));
        assertThat(saludo, containsStringIgnoringCase("Hola todo"));

        assertThat(saludo, startsWith("Hola"));
        assertThat(saludo, endsWith("mundo"));
    }

    @Test
    public void ejemploMatcherCoreApi() {

        // matcher de negacion - pasa si no se cumple la condicion
        assertThat("Hola", not("Adios"));
        assertThat("Hola", not(Matchers.emptyOrNullString()));

        // matchers donde se deben cumplir varias condiciones a la vez. Pasara si cumple todas las condiciones
        assertThat("Hola", allOf(
                startsWith("H"),
                endsWith("a"),
                containsString("ol")));

        // AnyOf - matcher que pasa si se cumple al menos una de las condiciones, incluso si no cumple alguna, pero cumple al menos una
        assertThat("Hola", anyOf(
                startsWith("M"),
                endsWith("a"),
                containsString("ol")));

        // queremos determinar que en una coleccion, todos los elementos cumplan cierta condicion - matcher everyItem
        List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5);
        assertThat(numeros, everyItem(greaterThan(0)));
    }

    @Test
    public void ejemploMatcherPersonalizado() {
        assertThat(8, isNumeroPositivo.esNumeroPosititvo());
        // matcher personalizado que verifica si un numero es positivo

        assertThat(8, RangoMatcher.estaEntreRango(2, 10));

        assertThat(8, MatcherPersonalizado.matchesInteger(Matchers.lessThan(10)));
    }


}
