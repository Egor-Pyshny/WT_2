package com.example.demo1.exceptions;

public class CommandException extends ProjectException {

    public CommandException(String message) {
        super(message);
    }

    public CommandException(String message, Exception ex) {
        super(message, ex);
    }
}
