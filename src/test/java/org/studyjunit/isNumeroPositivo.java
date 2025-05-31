package org.studyjunit;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class isNumeroPositivo extends TypeSafeMatcher<Integer> {

    // Método que se ejecuta antes de la prueba para validar el tipo de dato
    @Override
    protected boolean matchesSafely(Integer item) {
        if (item > 0) {
            return true;
        }
        return false;
    }

    // Mensaje que se mostrara en la trasa de fallos cuando falle la prueba
    @Override
    public void describeTo(Description description) {
        description.appendText("Deberia ser un numero positivo");
    }

    public static isNumeroPositivo esNumeroPosititvo() {
        return new isNumeroPositivo();
    }
}
