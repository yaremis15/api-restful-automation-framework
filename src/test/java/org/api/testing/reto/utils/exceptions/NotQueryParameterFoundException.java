package org.api.testing.reto.utils.exceptions;

public class NotQueryParameterFoundException extends RuntimeException {

    public NotQueryParameterFoundException(String queryParametersOption) {
        super("No se ha encontrado el parámetro de consulta: " + queryParametersOption);
    }
}
