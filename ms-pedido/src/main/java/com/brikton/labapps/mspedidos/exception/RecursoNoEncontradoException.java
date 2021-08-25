package com.brikton.labapps.mspedidos.exception;

public class RecursoNoEncontradoException extends Exception {

    private static final long serialVersionUID = 1L;

    public RecursoNoEncontradoException(String string, Integer id) {
        super(string + id);
    }
}
