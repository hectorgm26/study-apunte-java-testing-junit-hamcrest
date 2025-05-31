package org.studyjunit;

import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregationException;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;

public class PersonaAggregator implements ArgumentsAggregator {

    // Recibe el contexto del parametro, y un accesor de argumentos que contiene los valores de los argumentos pasados al test.
    // Dentro de ese objeto unico tendremos los parametros de la prueba
    // Se obtienen mediante el indice de los argumentos, y se asignan a un objeto Persona

    @Override
    public Object aggregateArguments(ArgumentsAccessor accessor, ParameterContext context) throws ArgumentsAggregationException {

        String nombre = accessor.getString(1);
        String apellido = accessor.getString(2);

        Persona persona = new Persona(nombre, apellido);
        return persona;
    }
}
