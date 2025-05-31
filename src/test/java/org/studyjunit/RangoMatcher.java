package org.studyjunit;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class RangoMatcher extends TypeSafeMatcher<Integer> {

    // Comprobaremos que el dato de entrada por parametro se encuentra dentro de un rango
    private int limiteInferior;
    private int limiteSuperior;

    // Constructor que recibe los limites del rango
    public RangoMatcher(int limiteInferior, int limiteSuperior) {
        this.limiteInferior = limiteInferior;
        this.limiteSuperior = limiteSuperior;
    }

    @Override
    protected boolean matchesSafely(Integer item) {
        if (limiteInferior <= item && limiteSuperior >= item) {
            return true;
        }
        return false;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("Deberia estar entre " + limiteInferior + " y " + limiteSuperior);
    }

    public static RangoMatcher estaEntreRango(int valorInferior, int valorSuperior) {
        return new RangoMatcher(valorInferior, valorSuperior);
    }
}
