package com.dailycodework.ezpark.exception;

/**
 * Excepción que se lanza cuando ya existe un rol.
 */
public class RolYaExisteException extends RuntimeException {
    public RolYaExisteException(String message) {
        super(message);
    }
}
