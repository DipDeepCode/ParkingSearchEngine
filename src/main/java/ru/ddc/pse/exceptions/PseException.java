package ru.ddc.pse.exceptions;

import lombok.Getter;

@Getter
public class PseException extends Exception {
    private final String message;

    public PseException(String message) {
        super();
        this.message = message;
    }
}
