package org.studyjunit;

import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class TestIntegracion {

    @Test
    public void testQueSeEjecutaSiempre() {
        System.out.println("Se ejecuta el test 1");
    }

    @Test
    public void testCondicional() {
        Assumptions.assumeTrue("CI".equals(System.getenv("ENV")));
        // Establecer variable de entorno -> set ENV=CI (en terminal)
        // echo %ENV% (para verificar la variable de entorno)
        // mvn test -Dtest=TestIntegracion (para ejecutar el test de una clase específica)

        // set ENV=TEST (para CAMBIAR la variable de entorno y no ejecutar este test, saltandolo
        System.out.println("Se ejecuta el test condicional en integracion continua");
    }

    @Test
    public void testCondicional2() {
        Assumptions.assumeFalse("CI".equals(System.getenv("ENV")));

        // set ENV=TEST (como es assumeFalse, si la variable de entorno es CI, no se ejecuta el test)
        System.out.println("Se ejecuta el test condicional en integracion continua");
    }

    @Test
    public void testCondicional3() {
        Assumptions.assumingThat("CI".equals(System.getenv("ENV")), () -> System.out.println("Estamos en CI!"));

        System.out.println("Test Condicional 3 ejecutado");
    }

    @Test
    public void testAbort() {
        Assumptions.abort();

        System.out.println("Este test no se ejecutará porque se ha abortado la ejecución");
    }

    @Test
    @Tag("integrationTest")
    public void testIntegracion() {
        System.out.println("Este es un test de integración marcado con la etiqueta 'integrationTest'");
    }

    @Test
    @Tag("performanceTest")
    public void testRendimiento() {
        System.out.println("Este es un test de rendimiento marcado con una etiqueta vacía");
    }
}
