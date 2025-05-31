package org.studyjunit;

import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ArgumentConverter;

import java.time.LocalDate;

public class DateConverter implements ArgumentConverter {

    // Recibe un parameterContext, que contiene informacion del parametro que se esta convirtiendo
    // Y un objeto source, que es el valor que se esta convirtiendo,
    // y lo que hace es convertir ese objeto source, al del tipo que se tornara

    @Override
    public Object convert(Object source, ParameterContext context) throws ArgumentConversionException {
        // Convertiremos un string recibido para convertirlo a un objeto localDate
        if (!(source instanceof String)) {
            throw new IllegalArgumentException("El argumento debe ser un String" + source);
        }
        // año/mes/dia  - 2024/01/01   - Para ello se debe descomponer con un split y capturar los valores
        try {
            // se convierte el source en string y le aplicamos el split para separar los valores y obtener las partes
            String[] partes = ((String) source).split("/");
            int year = Integer.parseInt(partes[0]);
            int month = Integer.parseInt(partes[1]);
            int day = Integer.parseInt(partes[2]);

            return LocalDate.of(year, month, day);
            
        } catch (Exception e) {
            throw new IllegalArgumentException("Fallo la conversion" + e);
        }
    }
}
