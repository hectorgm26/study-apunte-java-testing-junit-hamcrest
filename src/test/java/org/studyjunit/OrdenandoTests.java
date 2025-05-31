package org.studyjunit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OrdenandoTests {

    @Test
    @DisplayName("Test A")
    @Order(3)
    public void testUno() {
        System.out.println("Test Uno");
    }

    @Test
    @DisplayName("Test B")
    @Order(2)
    public void testDos() {
        System.out.println("Test Dos");
    }

    @Test
    @DisplayName("Test C")
    @Order(1)
    public void testTres() {
        System.out.println("Test Tres");
    }

    @Test
    @DisplayName("Test D")
    // Si no le colocamos un @Order(), se ejecuta al final
    public void testCuatro() {
        System.out.println("Test Cuatro");
    }

}
