package org.studyjunit;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.support.AnnotationConsumer;

import java.lang.reflect.Field;
import java.util.stream.Stream;

public class VariableArgumentsProvider implements ArgumentsProvider, AnnotationConsumer<VariableSource> {

    private String variableName;

    @Override
    public void accept(VariableSource variableSource) {
        // Agarra la variable value de la anotacion VariableSource y guardarla en un sitio
        variableName = variableSource.value();
    }

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
        // Aqui se implementa la logica para generar los argumentos
        // Por ejemplo, podríamos usar la variableName para obtener datos de una fuente externa
        // o simplemente retornar un Stream de Arguments con datos de prueba.

        return context.getTestClass()
                .map(this::getField)
                .map(this::getValue)
                .orElseThrow(() -> new IllegalArgumentException("Failed to load test arguments"));
    }

    private Field getField(Class<?> clazz) {
        try {
            return clazz.getDeclaredField(variableName);
        } catch (Exception e) {
            return null;
        }
    }

    private Stream<Arguments> getValue(Field field) {
        Object value = null;
        try {
            value = field.get(null); // Obtener el valor de la variable estática
        } catch (Exception ignored) {
        }
        return value == null ? null : (Stream<Arguments>) value;
    }
}
