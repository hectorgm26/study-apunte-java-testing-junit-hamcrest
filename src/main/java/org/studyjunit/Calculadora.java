package org.studyjunit;

public class Calculadora {

    public boolean esImpar(int valor) {
        return valor % 2 != 0;
    }

    public int sumar(int valor1, int valor2) {
        return valor1 + valor2;
    }

    public int multiplicar(int valor1, int valor2) {
        return valor1 * valor2;

        /* lA LOGICA PODRIA CAMBIAR
        int resultado = 0;
        for (int i = 1; i <= valor2; i++) {
            resultado = resultado + valor1;
        }
         */
    }

    public int dividir(int valor1, int valor2) {
        return valor1 / valor2;
    }
}