package org.studyjunit;

public class Cadenas {

    public boolean esBlanco(String cadena) {
        return cadena == null || cadena.trim().isEmpty();
        // Retorna true si la cadena es nula o está vacía (después de eliminar espacios en blanco)
    }

    public String pasarMayuscla(String cadena) {
        return cadena.toUpperCase();
    }
}
